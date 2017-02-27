package com.redstar.jjd.admin.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.redstar.jjd.admin.service.MenuService;
import com.redstar.jjd.admin.service.OperatorService;
import com.redstar.jjd.model.Menu;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.utils.MenuPermUtil;
import com.redstar.jjd.vo.TreeNode;

/**
 * 
 * ClassName: MenuController <br/>
 * Date: 2016年6月23日 下午5:53:30 <br/>
 * Description: 菜单展现 <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping("/menu.do")
public class MenuController {
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(params = { "method=getUserMenu" })
    @ResponseBody
    public List<TreeNode> getUserTreeMenu(HttpServletRequest request) {
        String contextPath = request.getSession().getServletContext()
                .getContextPath();

        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        Operator currUser = operatorService.getOperatorByLoginName(loginName);

        MenuPermUtil.parseUserMenu(menuService.getRootMenuByOrder(),
                treeNodeList, parseMenuIds(currUser.getRoleList()),
                contextPath, messageSource,
                RequestContextUtils.getLocale(request));
        return treeNodeList;
    }

    /**
     * 获取角色的所有的菜单
     * 
     * @param roles
     * @return
     */
    private Set<Long> parseMenuIds(List<Role> roles) {
        Set<Long> ids = new HashSet<Long>();
        for (Role role : roles) {
            for (Permission perm : role.getPermissions()) {
                recParseMenuIds(ids, perm.getMenu());
            }
        }
        return ids;

    }

    /**
     * 递归菜单
     * 
     * @param menuIds
     * @param menu
     */
    private void recParseMenuIds(Set<Long> menuIds, Menu menu) {
        menuIds.add(menu.getMenuId());
        if (null != menu.getParent()) {
            recParseMenuIds(menuIds, menu.getParent());
        }
    }
}
