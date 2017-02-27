/**
 * Project Name:jjd-web
 * File Name:UserLoanController.java
 * Package Name:com.redstar.jjd.admin.controller
 * Date:2016年11月24日下午3:50:14
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
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.redstar.jjd.admin.service.LoanExamineService;
import com.redstar.jjd.admin.service.LoanIntentionService;
import com.redstar.jjd.admin.service.LoanIntoService;
import com.redstar.jjd.admin.service.StoreService;
import com.redstar.jjd.admin.service.UserLoanService;
import com.redstar.jjd.controller.UserInfoController;
import com.redstar.jjd.query.UserOnlineLoanQuery;
import com.redstar.jjd.query.UserSpecialLoanQuery;
import com.redstar.jjd.security.ShiroDbRealm;
import com.redstar.jjd.security.ShiroDbRealm.ShiroUser;
import com.redstar.jjd.utils.ExportUtil;
import com.redstar.jjd.vo.LoanExamineView;
import com.redstar.jjd.vo.LoanIntentionView;
import com.redstar.jjd.vo.LoanIntoView;
import com.redstar.jjd.vo.StoreView;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserLoanView;

/**
 * ClassName: UserLoanController <br/>
 * Date: 2016年11月24日 下午3:50:14 <br/>
 * Description: 家居贷客户信息管理
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/userLoan")
public class UserLoanController {
    private static final Logger LOGGER = Logger
            .getLogger(UserInfoController.class);

    @Autowired
    private UserLoanService userLoanService;

    @Autowired
    private LoanIntentionService loanIntentionService;

    @Autowired
    private LoanIntoService loanIntoService;

    @Autowired
    private LoanExamineService loanExamineService;

    @Autowired
    private StoreService storeService;

    /**
     * 
     * @description 跳转到线分期查询页面 <br/>
     * 
     * @return String
     * @throws
     */
    @RequestMapping(value = "/searchOnline")
    public String listOnlinePage() {
        return "user.online.list";
    }

    /**
     * 
     * @description 查询在线持卡分期客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=queryOnline" })
    @ResponseBody
    public Map<String, Object> queryWechat(UserOnlineLoanQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserApplyView> list = new ArrayList<UserApplyView>();
        HttpSession session = req.getSession();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            String roleName = (String) session.getAttribute("roleName");
            query.setRoleName(roleName);
            list = userLoanService.queryOnline(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询异常");
        }
        result.put("total", query.getTotalCount());
        result.put("rows", list);
        return result;
    }

    /**
     * 
     * @description 在线分期客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=exportOnline" })
    public void exportOnline(UserOnlineLoanQuery query, HttpServletRequest req,
            HttpServletResponse resp) {
        List<UserApplyView> list = new ArrayList<UserApplyView>();
        WritableWorkbook workbook = null;
        HttpSession session = req.getSession();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            String roleName = (String) session.getAttribute("roleName");
            query.setRoleName(roleName);
            list = userLoanService.queryOnlineExport(query);
            workbook = userLoanService.createExcelForOnLine(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷_在线持卡分期信息表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    /**
     * 
     * @description 跳转到专项申请查询页面 <br/>
     * 
     * @return String
     * @throws
     */
    @RequestMapping(value = "/searchSpecialLoan")
    public String listSpecialPage() {
        return "user.special.list";
    }

    /**
     * 
     * @description 查询专项贷款客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=querySpecialLoan" })
    @ResponseBody
    public Map<String, Object> querySpecialLoan(UserSpecialLoanQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserApplyView> list = new ArrayList<UserApplyView>();
        HttpSession session = req.getSession();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            String roleName = (String) session.getAttribute("roleName");
            query.setRoleName(roleName);
            list = userLoanService.querySpecial(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询异常");
        }
        result.put("total", query.getTotalCount());
        result.put("rows", list);
        return result;
    }

    /**
     * 
     * @description 专项申请客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=exportSpecialLoan" })
    public void exportSpecialLoan(UserSpecialLoanQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        List<UserApplyView> list = new ArrayList<UserApplyView>();
        HttpSession session = req.getSession();
        WritableWorkbook workbook = null;
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            String roleName = (String) session.getAttribute("roleName");
            query.setRoleName(roleName);
            list = userLoanService.querySpecialExport(query);
            workbook = userLoanService.createExcelForSpecial(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷_专项申请信息表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    /**
     * 
     * @description 保存是否有贷款意向 <br/>
     * 
     * @param view
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=saveLoan" })
    @ResponseBody
    public Map<String, Object> saveLoan(UserLoanView view) {
        Map<String, Object> map = Maps.newHashMap();
        LoanIntentionView intentionView = view.getLoanIntentionView();
        String msg = "";
        Boolean success = Boolean.TRUE;
        ShiroUser shiroUser = ShiroDbRealm.getCurrentUser();
        intentionView.setOperatorId(shiroUser.getOperator().getOperatorId());
        try {
            loanIntentionService.save(intentionView);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save loanIntention fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    /**
     * 
     * @description 保存是否已进件 <br/>
     * 
     * @param view
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=saveLoanInto" })
    @ResponseBody
    public Map<String, Object> saveLoanInto(UserLoanView view) {
        Map<String, Object> map = Maps.newHashMap();
        LoanIntoView intoView = view.getLoanIntoView();
        String msg = "";
        Boolean success = Boolean.TRUE;
        ShiroUser shiroUser = ShiroDbRealm.getCurrentUser();
        intoView.setOperatorId(shiroUser.getOperator().getOperatorId());
        try {
            loanIntoService.save(intoView);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save loanInto fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    /**
     * 
     * @description 保存是否审批已通过 <br/>
     * 
     * @param view
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=saveLoanExamine" })
    @ResponseBody
    public Map<String, Object> saveLoanExamine(UserLoanView view) {
        Map<String, Object> map = Maps.newHashMap();
        LoanExamineView examineView = view.getLoanExamineView();
        String msg = "";
        Boolean success = Boolean.TRUE;
        ShiroUser shiroUser = ShiroDbRealm.getCurrentUser();
        examineView.setOperatorId(shiroUser.getOperator().getOperatorId());
        try {
            loanExamineService.save(examineView);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save loanExamine fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    /**
     * 商场录入弹出框显示
     * */
    @RequestMapping(params = "method=addStoreLoanPage")
    public String createPtPage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
        Long storeId = (Long) session.getAttribute("storeId");
        if (storeFlag != null && storeFlag) {
            StoreView storeView = storeService.findByStoreId(storeId);
            session.setAttribute("storeView", storeView);
        }
        session.setAttribute("storeId", storeId);
        return "loan.store.add";
    }

    /**
     * 
     * @description 新增顾客意向贷款信息 <br/>
     * 
     * @param view
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=saveSpecialLoan" })
    @ResponseBody
    public Map<String, Object> saveSpecialLoan(UserApplyView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        ShiroUser shiroUser = ShiroDbRealm.getCurrentUser();
        view.setOperatorId(shiroUser.getOperator().getOperatorId());
        try {
            userLoanService.saveSpecialLoan(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save specialLoan info fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    /**
     * 
     * @description 跳转到专项申请标记备注详情页面<br/>
     * 
     * @return String
     * @throws
     */

    @RequestMapping(params = "method=detailLoanPage")
    public String detailLoanPage(HttpServletRequest req, Long applyId) {
        UserLoanView view = new UserLoanView();
        try {
            view = userLoanService.findUserLoanById(applyId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询异常");
        }
        HttpSession session = req.getSession();
        session.setAttribute("userLoan", view);
        return "user.detailLoan";
    }

}
