/**
 * Project Name:jjd-web
 * File Name:RoleDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2016年6月20日下午5:00:14
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao;

import java.util.List;

import com.redstar.jjd.dao.BaseDao;
import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;

/**
 * ClassName: RoleDao <br/>
 * Date: 2016年6月20日 下午5:00:14 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface RoleDao extends BaseDao<Role, Long> {
    int delBatch(String ids);

    Role getRoleByName(String name);

    void updateRole(Role role, List<Permission> permList);

    /**
     * 删除角色权限记录
     * 
     * @param role
     * */
    public Integer deleteR2PByRoleId(Role role);

}
