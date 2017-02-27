package com.redstar.jjd.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.redstar.jjd.admin.dao.OperatorDao;
import com.redstar.jjd.admin.dao.RoleDao;
import com.redstar.jjd.admin.service.PermissionService;
import com.redstar.jjd.admin.service.RoleService;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.query.RoleQuery;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.RoleView;

/**
 * 
 * ClassName: RoleSerivceImpl <br/>
 * Date: 2016年6月23日 下午5:02:53 <br/>
 * Description: 角色管理serviceImpl <br/>
 * 
 * @author huangrui
 * @version
 * @seelist
 */
@Service("roleService")
public class RoleSerivceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<Role> query(RoleQuery roleQuery) {
        List<Role> list = roleDao.pageQuery(roleQuery);
        return list;
    }

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return roleDao.get(roleId);
    }

    @Override
    public void updateRole(Role role, List<Permission> permList) {
        Long roleId = role.getRoleId();
        if (null != role.getRoleId()) {
            Role exit = roleDao.get(roleId);
            exit.getPermissions();
            exit.setRoleName(role.getRoleName());
            exit.setPermissions(null);
            exit.setPermissions(permList);
            roleDao.update(exit);
        } else {
            role.setPermissions(permList);
            roleDao.save(role);
        }
    }

    @Override
    public Map<String, Object> delBatch(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            Role role = roleDao.get(Long.parseLong(id));
            List<Object[]> userIds = operatorDao.findUsersByRoleId(role
                    .getRoleId());
            if (userIds == null || userIds.size() == 0) {
                Integer retInt = roleDao.deleteR2PByRoleId(role);
                if (retInt != null) {
                    roleDao.delete(role.getRoleId());
                }
            } else {
                msg = "此角色下有用户关联，无法删除。";
            }
        }
        int total = idArr.length;
        map.put("msg", msg);
        map.put("total", total);
        return map;
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<RoleView> findAll() {
        List<Role> list = roleDao.listAll();
        List<RoleView> viewList = ConvertUtils
                .convertList(list, RoleView.class);
        return viewList;
    }

}
