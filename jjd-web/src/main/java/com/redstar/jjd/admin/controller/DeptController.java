/**
 * Project Name:jjd-web
 * File Name:DepartmentController.java
 * Package Name:com.redstar.jjd.admin.controller
 * Date:2016年9月14日下午3:32:43
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.redstar.jjd.admin.service.DeptService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Dept;
import com.redstar.jjd.query.DeptQuery;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.DeptView;

/**
 * ClassName: DepartmentController <br/>
 * Date: 2016年9月14日 下午3:32:43 <br/>
 * Description: 部门管理
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/admin/dept.do")
public class DeptController {
    private static final Logger LOGGER = Logger.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @RequestMapping(params = { "method=getAll" })
    @ResponseBody
    public List<ComboVO> getAll() {
        List<Dept> list = deptService.findAll();
        List<ComboVO> retDeptlist = new ArrayList<ComboVO>();
        for (Entry<String, String> entry : GeneralConstant.selectMap.entrySet()) {
            ComboVO vo = new ComboVO(entry.getKey(), entry.getValue());
            retDeptlist.add(vo);
        }
        for (Dept dept : list) {
            retDeptlist.add(new ComboVO(dept.getDeptId().toString(), dept
                    .getDeptName()));
        }
        return retDeptlist;
    }

    // @RequiresPermissions("dept:view")
    @RequestMapping(params = { "method=list" })
    public String list(Model model) {
        return "dept.list";
    }

    @RequestMapping(params = { "method=query" })
    @ResponseBody
    public Map<String, Object> query(Model model, DeptQuery query) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<Dept> depts = new ArrayList<Dept>();
        depts = deptService.query(query);
        retMap.put("total", query.getTotalCount());
        retMap.put("rows", depts);
        return retMap;
    }

    // @RequiresPermissions({ "dept:add", "dept:update" })
    @RequestMapping(params = { "method=save" })
    @ResponseBody
    public Map<String, Object> save(DeptView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            deptService.save(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save dept fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    // @RequiresPermissions("dept:delete")
    @RequestMapping(params = "method=batchDel")
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        int result = 0;
        try {
            result = deptService.delBatch(ids);
            if (result == -1) {
                map.put("msg", "该部门有账户存在,不能删除");
                return map;
            }
            map.put("msg", "删除成功");
        } catch (Exception e) {
            success = Boolean.FALSE;
            map.put("msg", "删除失败");
        }
        map.put("success", success);
        return map;
    }

    /**
     * 判断该名称是否存在
     */
    @RequestMapping(params = { "method=isNameExist" })
    @ResponseBody
    public boolean isNameExist(String deptName, Long deptId) {
        if (null != deptId) {
            Dept dept = deptService.findBy(deptId);
            if (deptName.equals(dept.getDeptName())) {
                return true;
            } else {
                return false;
            }
        } else {
            List<Dept> list = deptService.findBy(deptName);
            if (!list.isEmpty() && list != null) {
                System.out.println("11111111111");
                return false;
            }
        }
        return true;
    }
}
