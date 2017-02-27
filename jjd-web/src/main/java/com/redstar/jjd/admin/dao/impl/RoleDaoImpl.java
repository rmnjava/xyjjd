/**
 * Project Name:jjd-web
 * File Name:RoleDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年6月20日下午5:00:40
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.RoleDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;

/**
 * ClassName: RoleDaoImpl <br/>
 * Date: 2016年6月20日 下午5:00:40 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

    @Override
    public int delBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            Role role = get(Long.parseLong(id));
            if (null != role) {
                delete(role.getRoleId());
            }
        }
        return idArr.length;

    }

    @Override
    public Role getRoleByName(String name) {
        return findUniqueBy("roleName", name);
    }

    @Override
    public void updateRole(Role role, List<Permission> permList) {
        Long roleId = role.getRoleId();
        if (null != role.getRoleId()) {
            Role exit = get(roleId);
            exit.getPermissions();
            exit.setRoleName(role.getRoleName());
            exit.setPermissions(null);
            exit.setPermissions(permList);
            this.saveOrUpdate(exit);
        } else {
            role.setPermissions(permList);
            this.save(role);
        }

    }

    /**
     * 删除角色权限记录
     * 
     * @param role
     * */
    public Integer deleteR2PByRoleId(Role role) {
        Integer retInt = null;
        StringBuffer sql = new StringBuffer();
        sql.append(" DELETE FROM t_sys_r2p WHERE role_id = ?");
        Query query = super.getSession().createSQLQuery(sql.toString());
        query.setLong(0, role.getRoleId());
        retInt = query.executeUpdate();
        return retInt;
    }

}
