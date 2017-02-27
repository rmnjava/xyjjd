/**
 * Project Name:jjd-web
 * File Name:OperatorController.java
 * Package Name:com.redstar.jjd.admin.controller
 * Date:2016年7月1日下午5:14:53
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.redstar.jjd.admin.service.MenuService;
import com.redstar.jjd.admin.service.OperatorService;
import com.redstar.jjd.admin.service.RoleService;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.query.OperatorQuery;
import com.redstar.jjd.query.RoleQuery;
import com.redstar.jjd.security.ShiroDbRealm;
import com.redstar.jjd.security.ShiroDbRealm.ShiroUser;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.utils.ExportUtil;
import com.redstar.jjd.utils.MD5;
import com.redstar.jjd.vo.OperatorView;
import com.redstar.jjd.vo.RoleView;

/**
 * ClassName: OperatorController <br/>
 * Date: 2016年7月1日 下午5:14:53 <br/>
 * Description: 账号登录controller类
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/admin/operator.do")
public class OperatorController {
    private static final Logger LOGGER = Logger.getLogger(RoleController.class);

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    // @RequiresPermissions("operator:view")
    @RequestMapping(params = { "method=list" })
    public String list(Model model) {
        return "operator.list";
    }

    @RequestMapping(params = { "method=showUpdate" })
    @ResponseBody
    public Operator getById(HttpServletRequest req, Long operatorId) {
        Operator operator = operatorService.findById(operatorId);
        req.setAttribute("operator", operator);
        return operator;
    }

    // @RequiresPermissions("operator:view")
    @RequestMapping(params = { "method=query" })
    @ResponseBody
    public Map<String, Object> query(Model model, OperatorQuery query) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<OperatorView> operators = new ArrayList<OperatorView>();
        operators = operatorService.query(query);
        retMap.put("total", query.getTotalCount());
        retMap.put("rows", operators);
        return retMap;
    }

    /**
     * 角色新增弹出框显示
     * */
    @RequestMapping(params = "method=createOperatorPage")
    public String createPtPage(Model model, RoleQuery query) {
        return "operator.create";
    }

    /**
     * 角色修改弹出框显示
     * */
    @RequestMapping(params = "method=updateOperatorPage")
    public String updatePtPage(Model model, Long operatorId) {
        Operator operator = operatorService.findById(operatorId);
        model.addAttribute("operator", operator);
        return "operator.update";
    }

    // @RequiresPermissions({ "operator:add", "operator:update" })
    @RequestMapping(params = { "method=save" })
    @ResponseBody
    public Map<String, Object> save(String role, OperatorView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        List<Role> roleList = new ArrayList<Role>();
        if (null != role && role.length() != 0) {
            for (String roleId : role.split(",")) {
                Role roleE = roleService.findRoleByRoleId(Long
                        .parseLong(roleId));
                roleList.add(roleE);

            }
            List<RoleView> roles = ConvertUtils.convertList(roleList,
                    RoleView.class);
            view.setRoleList(roles);
        }
        try {
            operatorService.save(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save operator fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    // @RequiresPermissions({ "operator:update" })
    @RequestMapping(params = { "method=update" })
    @ResponseBody
    public Map<String, Object> update(OperatorView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            operatorService.update(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("update operator fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    @RequiresPermissions("operator:delete")
    @RequestMapping(params = "method=batchDel")
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        try {
            operatorService.delBatch(ids);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            success = Boolean.FALSE;
            map.put("msg", "删除失败");
        }
        map.put("success", success);
        return map;
    }

    /**
     * 
     * @description 重置密码 <br/>
     * 
     * @param request
     * @return String
     * @throws
     */
    @RequestMapping(params = "method=showResetPwdPage")
    public String showResetPwdPage(HttpServletRequest request) {
        ShiroUser currentUsesr = ShiroDbRealm.getCurrentUser();
        if (currentUsesr != null) {
            request.setAttribute("userMD5Pwd", currentUsesr.getOperator()
                    .getPassword());
        }
        return "user.resetpwd";
    }

    @RequestMapping(params = "method=resetPwd")
    public @ResponseBody
    Map<String, Object> resetPassword(HttpServletRequest request,
            OperatorView view) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        ShiroUser currentUsesr = ShiroDbRealm.getCurrentUser();
        OperatorView operator = currentUsesr.getOperator();
        Boolean flag = true;
        // 页面输入的原密码
        String oldpassword = request.getParameter("oldpassword");
        String MD5Password = MD5.GetMD5Code(oldpassword);
        if (oldpassword != null && MD5Password.equals(operator.getPassword())) {
            // 输入的新密码md5加密
            String md5Pwd = MD5.GetMD5Code(view.getPassword());
            operator.setPassword(md5Pwd);
            try {
                operatorService.update(operator);
                Subject currentUser = SecurityUtils.getSubject();
                currentUser.logout();
                retMap.put("success", flag);
                retMap.put("msg", "重置密码成功");
            } catch (Exception e) {
                flag = false;
                retMap.put("success", flag);
                retMap.put("msg", "修改密码失败");
            }
        } else {
            retMap.put("msg", "原密码不正确,请重新输入");
        }

        return retMap;
    }

    /**
     * 判断该名称是否存在
     */
    @RequestMapping(params = { "method=isNameExist" })
    @ResponseBody
    public boolean isNameExist(String loginName, Long operatorId) {
        if (null != operatorId) {
            Operator operator = operatorService.findById(operatorId);
            if (loginName.equals(operator.getLoginName())) {
                return true;
            } else {
                return false;
            }
        } else {
            List<Operator> list = operatorService.findByName(loginName);
            if (!list.isEmpty() && list != null) {
                System.out.println("11111111111");
                return false;
            } else {
                return true;
            }
        }

    }

    /**
     * 
     * @description 账户导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=exportOperator" })
    public void exportOperator(OperatorQuery query, HttpServletRequest req,
            HttpServletResponse resp) {
        List<OperatorView> list = new ArrayList<OperatorView>();
        WritableWorkbook workbook = null;
        try {
            list = operatorService.queryExport(query);
            workbook = operatorService.createExcelForOperator(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷_账户信息表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }
}
