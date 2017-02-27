package com.redstar.jjd.admin.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.redstar.jjd.admin.service.MenuService;
import com.redstar.jjd.admin.service.RoleService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Menu;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.query.RoleQuery;
import com.redstar.jjd.utils.MenuPermUtil;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.RoleView;
import com.redstar.jjd.vo.TreeNode;

/**
 * 
 * ClassName: RoleController <br/>
 * Date: 2016年6月20日 下午4:54:04 <br/>
 * Description: 角色controller <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/admin/role.do")
public class RoleController {
    private static final Logger LOGGER = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping(params = { "method=list" })
    public String list(Model model) {
        return "role.list";
    }

    // @RequiresPermissions("role:view")
    @RequestMapping(params = { "method=query" })
    @ResponseBody
    public Map<String, Object> query(Model model, RoleQuery query) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<Role> roles = new ArrayList<Role>();
        roles = roleService.query(query);
        retMap.put("total", query.getTotalCount());
        retMap.put("rows", roles);
        return retMap;
    }

    /**
     * 角色新增弹出框显示
     * */
    @RequestMapping(params = "method=createRolePage")
    public String createPtPage(Model model, RoleQuery query) {
        return "role.create";
    }

    /**
     * 角色修改弹出框显示
     * */
    @RequestMapping(params = "method=updateRolePage")
    public String updatePtPage(Model model, Long roleId) {
        Role role = roleService.findRoleByRoleId(roleId);
        model.addAttribute("role", role);
        return "role.update";
    }

    // @RequiresPermissions({ "role:add", "role:update" })
    @RequestMapping(params = { "method=save" })
    @ResponseBody
    public Map<String, Object> save(Long id,
            @RequestParam("roleNameInput") String roleName,
            @RequestParam("nodes") String nodes) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        Role role;
        if (null != id) {
            role = roleService.findRoleByRoleId(id);
        } else {
            role = new Role();
            role.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        role.setRoleName(roleName);
        String[] nodeArr = nodes.split(",");
        List<Permission> perms = new ArrayList<Permission>();
        for (String node : nodeArr) {
            String[] arr = node.split("\\:");
            if (arr.length < 4) {
                continue;
            }
            Long menuId = Long.parseLong(arr[0]);
            Menu menu = menuService.getById(menuId);
            Permission perm = new Permission();
            perm.setMenu(menu);
            perm.setOperation(arr[2]);
            perm.setPermissionId(Long.parseLong(arr[3]));
            perm.setResName(menu.getAliasName());
            perm.setResCnName(menu.getMenuName());
            perms.add(perm);
        }

        try {
            roleService.updateRole(role, perms);
            msg = "保存成功！";
        } catch (Exception e) {
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(params = "method=batchDel")
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        try {
            map = roleService.delBatch(ids);
            if (map.get("msg").equals("")) {
                map.put("msg", "删除成功");
            }
        } catch (Exception e) {
            success = Boolean.FALSE;
            map.put("msg", "删除失败");
        }
        map.put("success", success);
        return map;
    }

    @RequestMapping(params = { "method=delete" })
    @ResponseBody
    public String delete(Long id) {
        String message = null;
        try {
            roleService.delete(id);
            message = "角色删除成功！";
        } catch (Exception e) {
            LOGGER.info(e);
            message = "该角色已经使用,不能删除！";
        }
        return message;
    }

    @RequestMapping(params = { "method=getAllRoleName" })
    @ResponseBody
    public List<ComboVO> getRole4Combobox() {
        List<RoleView> list = roleService.findAll();
        List<RoleView> roleList = new ArrayList<RoleView>();
        roleList.addAll(list);
        List<ComboVO> retRolelist = new ArrayList<ComboVO>();
        for (Entry<String, String> entry : GeneralConstant.selectMap.entrySet()) {
            ComboVO vo = new ComboVO(entry.getKey(), entry.getValue());
            retRolelist.add(vo);
        }

        // 去除超级管理员(roleId=4050不可修改)
        for (RoleView role : list) {
            if (role.getRoleId() == 4050) {
                roleList.remove(role);
            }
        }
        for (RoleView d : roleList) {
            retRolelist.add(new ComboVO(d.getRoleId().toString(), d
                    .getRoleName()));
        }
        return retRolelist;
    }

    @RequestMapping(params = { "method=checkRoleName" })
    @ResponseBody
    public String checkRoleName(@RequestParam("roleNameInput") String roleName,
            @RequestParam("RoleIdExist") String roleId) {
        Role role = roleService.getRoleByName(roleName);
        if (null != role && !roleId.equals(role.getRoleId().toString())) {
            return "false";
        }
        return "true";
    }

    // @RequiresPermissions({ "role:add", "role:update" })
    @RequestMapping(params = { "method=getPermTree" })
    @ResponseBody
    public List<TreeNode> getPermTree(Long id) {
        List<TreeNode> permTree = new ArrayList<TreeNode>();
        if (null != id) {
            Role role = roleService.findRoleByRoleId(id);
            MenuPermUtil.parseMenuPermTree(menuService.getRootMenuByOrder(),
                    permTree, parsePermissionStrs(role.getPermissions()));
        } else {
            MenuPermUtil.parseMenuPermTree(menuService.getRootMenuByOrder(),
                    permTree);
        }
        return permTree;
    }

    private Set<String> parsePermissionStrs(List<Permission> permissions) {
        Set<String> permStrs = new HashSet<String>();
        for (Permission permssion : permissions) {
            Menu menu = menuService.getById(permssion.getMenu().getMenuId());
            permStrs.add(menu.getMenuId() + ":" + permssion.getResName() + ":"
                    + permssion.getOperation() + ":"
                    + permssion.getPermissionId());
        }
        return permStrs;
    }

}
