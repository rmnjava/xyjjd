package com.redstar.jjd.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.PermissionDao;
import com.redstar.jjd.admin.service.PermissionService;
import com.redstar.jjd.model.Permission;

/**
 * 
 * ClassName: PermissionSerivceImpl <br/>
 * Date: 2016年6月23日 下午5:17:29 <br/>
 * Description: 权限serviceImpl <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("permissionService")
public class PermissionSerivceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission findPermissionById(Long permissionId) {
        return permissionDao.get(permissionId);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.update(permission);
    }

}
