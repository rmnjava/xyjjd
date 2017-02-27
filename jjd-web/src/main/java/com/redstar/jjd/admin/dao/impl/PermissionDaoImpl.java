/**
 * Project Name:jjd-web
 * File Name:PermissionDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年6月23日下午6:32:12
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.PermissionDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.Permission;

/**
 * ClassName: PermissionDaoImpl <br/>
 * Date: 2016年6月23日 下午6:32:12 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission, Long> implements
        PermissionDao {

}
