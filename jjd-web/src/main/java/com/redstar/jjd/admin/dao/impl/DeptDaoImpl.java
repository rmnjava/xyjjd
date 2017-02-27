/**
 * Project Name:jjd-web
 * File Name:DepartmentDaoImpl.java
 * Package Name:com.redstar.jjd.admin.dao.impl
 * Date:2016年9月14日下午3:37:00
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.DeptDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.Dept;

/**
 * ClassName: DepartmentDaoImpl <br/>
 * Date: 2016年9月14日 下午3:37:00 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept, Long> implements DeptDao {

}
