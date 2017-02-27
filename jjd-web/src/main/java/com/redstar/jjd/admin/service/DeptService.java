/**
 * Project Name:jjd-web
 * File Name:DeptService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年9月14日下午3:44:15
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.util.List;

import com.redstar.jjd.model.Dept;
import com.redstar.jjd.query.DeptQuery;
import com.redstar.jjd.vo.DeptView;

/**
 * ClassName: DeptService <br/>
 * Date: 2016年9月14日 下午3:44:15 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface DeptService {

    public List<Dept> query(DeptQuery deptQuery);

    public Dept save(DeptView view);

    public int delBatch(String ids);

    public List<Dept> findBy(String deptName);

    public List<Dept> findAll();

    public Dept findBy(Long deptId);
}
