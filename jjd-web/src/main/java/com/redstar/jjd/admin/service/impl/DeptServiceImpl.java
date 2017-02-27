/**
 * Project Name:jjd-web
 * File Name:DeptServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年9月14日下午3:44:38
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.DeptDao;
import com.redstar.jjd.admin.dao.OperatorDao;
import com.redstar.jjd.admin.service.DeptService;
import com.redstar.jjd.model.Dept;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.query.DeptQuery;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.DeptView;

/**
 * ClassName: DeptServiceImpl <br/>
 * Date: 2016年9月14日 下午3:44:38 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<Dept> query(DeptQuery deptQuery) {
        List<Dept> list = deptDao.pageQuery(deptQuery);
        return list;
    }

    @Override
    public int delBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if (!id.isEmpty()) {
                // 判断该部门是否已有账号
                List<Operator> list = operatorDao.findBy("deptId",
                        Long.valueOf(id));
                if (!list.isEmpty()) {
                    return -1;
                } else {
                    deptDao.delete(Long.parseLong(id));
                }
            }
        }
        return idArr.length;
    }

    @Override
    public Dept save(DeptView view) {
        Dept dept = new Dept();
        ConvertUtils.convertObject(view, dept);
        if (view.getDeptId() != null) {
            return deptDao.update(dept);
        } else {
            return deptDao.save(dept);
        }
    }

    @Override
    public List<Dept> findBy(String deptName) {
        return deptDao.findBy("deptName", deptName);
    }

    @Override
    public List<Dept> findAll() {
        List<Dept> list = deptDao.listAll();
        return list;
    }

    @Override
    public Dept findBy(Long deptId) {
        return deptDao.get(deptId);
    }

}
