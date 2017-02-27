package com.redstar.jjd.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Menu;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.vo.TreeNode;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class MenuPermUtil {

    private static final Logger LOGGER = Logger.getLogger(MenuPermUtil.class);

    /**
     * 转化用户菜单为HTML格式
     * 
     * @param allMenus
     * @param userMenus
     * @param ids
     * @param contextPath
     */
    public static void parseUserMenu(List<Menu> allMenus,
            List<TreeNode> userMenus, Set<Long> ids, String contextPath,
            MessageSource messageSource, Locale locale) {
        for (Menu menu : allMenus) {
            if (ids.contains(menu.getMenuId())) {
                userMenus.add(recParseMenu(ids, menu, contextPath,
                        messageSource, locale));
            }
        }
    }

    /**
     * 递归菜单转化为HTML
     * 
     * @param ids
     * @param menu
     * @param contextPath
     * @param messageSource
     * @param locale
     * @return
     */
    private static TreeNode recParseMenu(Set<Long> ids, Menu menu,
            String contextPath, MessageSource messageSource, Locale locale) {
        TreeNode vo = new TreeNode();
        vo.setId(menu.getMenuId() + "");
        String text = "";
        String menuName = messageSource.getMessage(menu.getMenuName(), null,
                menu.getMenuName(), locale);
        if (null != menu.getMenuURL() && !"".equals(menu.getMenuURL())) {

            if (menu.getMenuURL().startsWith("/")) {
                text = "<a href ='" + contextPath + menu.getMenuURL() + "'>"
                        + menuName + "</a>";
            } else {
                text = "<a href ='" + contextPath + "/" + menu.getMenuURL()
                        + "'>" + menuName + "</a>";
            }
        } else {
            text = menuName;
        }
        vo.setText(text);
        if (!menu.getChildren().isEmpty()) {
            for (Menu childMenu : menu.getChildren()) {
                if (ids.contains(childMenu.getMenuId())) {
                    vo.getChildren().add(
                            recParseMenu(ids, childMenu, contextPath,
                                    messageSource, locale));
                }
            }
        }
        return vo;
    }

    /**
     * 转化所有的权限树形结构
     * 
     * @param allMenus
     * @param permTree
     * @param hasPerms
     */
    public static void parseMenuPermTree(List<Menu> allMenus,
            List<TreeNode> permTree) {
        List<Menu> menus = new ArrayList<Menu>();
        menus.addAll(allMenus);
        for (Menu menu : allMenus) {
            // 为了实现超级管理员永远只有一个，添加角色的时候去除系统管理权限(menuId=1000是系统管理不可修改)
            if (menu.getMenuId() == 1000) {
                menus.remove(menu);
            }
        }
        for (Menu menu : menus) {
            permTree.add(recPermTree(menu));

        }
    }

    private static TreeNode recPermTree(Menu menu) {
        TreeNode vo = new TreeNode();
        vo.setId(menu.getMenuId() + "");
        vo.setText(menu.getMenuName());
        if (!menu.getChildren().isEmpty()) {
            for (Menu childMenu : menu.getChildren()) {
                vo.getChildren().add(recPermTree(childMenu));
            }
        } else {
            for (Permission p : menu.getPermissions()) {
                TreeNode permNode = new TreeNode();
                permNode.setId(menu.getMenuId() + ":" + menu.getAliasName()
                        + ":" + p.getOperation() + ":" + p.getPermissionId());
                permNode.setText(GeneralConstant.operationMap.get(p
                        .getOperation()));
                vo.getChildren().add(permNode);
            }
        }
        return vo;
    }

    /**
     * 转化已有角色的权限树形结构
     * 
     * @param allMenus
     * @param permTree
     * @param hasPerms
     */
    public static void parseMenuPermTree(List<Menu> allMenus,
            List<TreeNode> permTree, Set<String> hasPerms) {
        List<Menu> menus = new ArrayList<Menu>();
        menus.addAll(allMenus);
        for (Menu menu : allMenus) {
            // 为了实现超级管理员永远只有一个，添加角色的时候去除系统管理权限(menuId=1000是系统管理不可修改)
            if (menu.getMenuId() == 1000) {
                menus.remove(menu);
            }
        }
        for (Menu menu : menus) {
            permTree.add(recMenuPermTree(menu, hasPerms));

        }
    }

    private static TreeNode recMenuPermTree(Menu menu, Set<String> hasPerms) {
        TreeNode vo = new TreeNode();
        vo.setId(menu.getMenuId() + "");
        vo.setText(menu.getMenuName());
        if (!menu.getChildren().isEmpty()) {
            for (Menu childMenu : menu.getChildren()) {
                vo.getChildren().add(recMenuPermTree(childMenu, hasPerms));
            }
        } else {
            for (Permission p : menu.getPermissions()) {
                TreeNode permNode = new TreeNode();
                String nodeId = menu.getMenuId() + ":" + menu.getAliasName()
                        + ":" + p.getOperation() + ":" + p.getPermissionId();
                permNode.setId(nodeId);
                if (hasPerms.contains(nodeId)) {
                    permNode.setChecked(true);
                }
                permNode.setText(GeneralConstant.operationMap.get(p
                        .getOperation()));
                vo.getChildren().add(permNode);
            }
        }
        return vo;
    }

    public static String parseMenuHTML(List<TreeNode> treeNodeList) {
        StringBuffer menuHTML = new StringBuffer("<ul id='hx-m'>");
        for (TreeNode node : treeNodeList) {
            MenuPermUtil.parseMenuHTML(menuHTML, node);
        }
        menuHTML.append("</ul>");
        LOGGER.debug(menuHTML);
        return menuHTML.toString();
    }

    public static void parseMenuHTML(StringBuffer menuHTML, TreeNode node) {
        menuHTML.append("<li>").append(node.getText());
        if (!node.getChildren().isEmpty()) {
            menuHTML.append("<ul>");
            for (TreeNode children : node.getChildren()) {
                parseMenuHTML(menuHTML, children);
            }
            menuHTML.append("</ul>");
        }
        menuHTML.append("</li>");
    }

    public static void printTreeNode(List<TreeNode> treeNodeList) {
        for (TreeNode node : treeNodeList) {
            MenuPermUtil.printTreeNode(node, 0);
        }
    }

    public static void printTreeNode(TreeNode node, int level) {
        String preStr = "|";
        for (int i = 0; i < level; i++) {
            preStr += "----";
        }
        for (TreeNode children : node.getChildren()) {
            printTreeNode(children, level + 1);
        }
    }
}
