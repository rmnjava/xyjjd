package com.redstar.jjd.admin.service;

import com.redstar.jjd.model.Permission;

/**
 * 
 * ClassName: PermissionService <br/>
 * Date: 2016年6月23日 下午4:59:05 <br/>
 * Description: 权限service <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface PermissionService {

    public Permission findPermissionById(Long permissionId);

    public void updatePermission(Permission permission);
}
