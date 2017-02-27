package com.redstar.jjd.controller;

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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redstar.jjd.query.UserApplyQuery;
import com.redstar.jjd.query.UserPayQuery;
import com.redstar.jjd.query.UserQuery;
import com.redstar.jjd.query.UserWechatQuery;
import com.redstar.jjd.query.UserWechatSpecialQuery;
import com.redstar.jjd.service.ApplyService;
import com.redstar.jjd.service.UserInfoService;
import com.redstar.jjd.service.UserPayService;
import com.redstar.jjd.utils.ExportUtil;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserInfoView;
import com.redstar.jjd.vo.UserPayView;

/**
 * 
 * ClassName: UserInfoController <br/>
 * Date: 2016年6月8日 下午3:01:15 <br/>
 * Description: 家居贷客户信息管理controller <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/user")
public class UserInfoController {

    private static final Logger LOGGER = Logger
            .getLogger(UserInfoController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private UserPayService userPayService;

    /**
     * 
     * @description 跳转到查询页面 <br/>
     * 
     * @return String
     * @throws
     */
    @RequestMapping(value = "/search")
    public String listPtPage() {
        return "user.list";
        // return "/user/index";
    }

    /**
     * 
     * @description 查询app支付成功客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=query" })
    // @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getInfos(UserQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserInfoView> list = new ArrayList<UserInfoView>();
        HttpSession session = req.getSession();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            list = userInfoService.query(query);
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
     * @description app支付成功客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=listExport1" })
    public void listExport1(UserQuery query, HttpServletRequest req,
            HttpServletResponse resp) {
        List<UserInfoView> list = new ArrayList<UserInfoView>();
        WritableWorkbook workbook = null;
        HttpSession session = req.getSession();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            list = userInfoService.queryAppExportList(query);
            workbook = userInfoService.createExcelForApp(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷app_农行成功分期表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    /**
     * 
     * @description 跳转到微信在线分期查询页面 <br/>
     * 
     * @return String
     * @throws
     */
    @RequestMapping(value = "/searchWechat")
    public String listWechatPage() {
        return "user.wechat.list";
        // return "/user/index";
    }

    /**
     * 
     * @description 查询微信在线分期客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=queryWechat" })
    // @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryWechat(UserWechatQuery query,
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
            list = userInfoService.queryWechat(query);
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
     * @description 微信在线分期客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=listExport2" })
    public void listExport2(UserWechatQuery query, HttpServletRequest req,
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
            list = userInfoService.queryWechatExport(query);
            workbook = userInfoService.createExcelForWeixin(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷微信_农行分期表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    @RequestMapping(value = "/search2")
    public String listPage() {
        return "user.nocard.list";
    }

    /**
     * 
     * @description 查询无卡申请贷款客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=query2" })
    @ResponseBody
    public Map<String, Object> getApplyInfo(UserApplyQuery query,
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
            list = applyService.query(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询异常");
        }
        result.put("total", query.getTotalCount());
        result.put("rows", list);
        // req.setAttribute("result", list);
        return result;
    }

    /**
     * 
     * @description app无卡申请贷款信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=listExport4" })
    public void listExport4(UserApplyQuery query, HttpServletRequest req,
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
            list = applyService.queryExport(query);
            workbook = applyService.createExcelForNoCard(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷app_无卡用户申请信息表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    @RequestMapping(value = "/searchSingle")
    public String searchSingle() {
        return "user.single.list";
    }

    /**
     * 
     * @description 查询农行单品成功客户信息 <br/>
     * 
     * @param query
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=querySingle" })
    @ResponseBody
    public Map<String, Object> querySingle(UserPayQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserPayView> list = new ArrayList<UserPayView>();
        try {
            list = userPayService.query(query);
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
     * @description 农行单品支付成功客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=listExport3" })
    public void listExport3(UserPayQuery query, HttpServletRequest req,
            HttpServletResponse resp) {
        List<UserPayView> list = new ArrayList<UserPayView>();
        WritableWorkbook workbook = null;
        try {
            list = userPayService.queryExport(query);
            workbook = userPayService.createExcelForSingle(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("农行单品_成功分期表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

    /**
     * 
     * @description 跳转到微信专项申请查询页面 <br/>
     * 
     * @return String
     * @throws
     */
    @RequestMapping(value = "/searchWechatSpecial")
    public String listWechatSpecialPage() {
        return "user.wechatspecial.list";
    }

    /**
     * 
     * @description 查询微信专项申请客户信息 <br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=queryWechatSpecial" })
    // @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryWechatSpecial(UserWechatSpecialQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = req.getSession();
        List<UserApplyView> list = new ArrayList<UserApplyView>();
        try {
            Boolean storeFlag = (Boolean) session.getAttribute("storeflag");
            Long storeId = (Long) session.getAttribute("storeId");
            if (storeFlag != null && storeFlag) {
                query.setStoreId(storeId);
            }
            list = userInfoService.queryWechatSpecial(query);
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
     * @description 微信专项申请客户信息导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=listWechatSpecialExport" })
    public void listWechatSpecialExport(UserWechatSpecialQuery query,
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
            list = userInfoService.queryWechatSpecialExport(query);
            workbook = userInfoService.createExcelForWechatSpecial(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷微信_专项申请表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

}
