/**
 * Project Name:jjd-web
 * File Name:BankController.java
 * Package Name:com.redstar.jjd.admin.controller
 * Date:2016年9月23日下午2:05:43
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
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Bank;
import com.redstar.jjd.model.BankProduct;
import com.redstar.jjd.model.BankProductReturnMethod;
import com.redstar.jjd.query.BankProductQuery;
import com.redstar.jjd.service.BankService;
import com.redstar.jjd.service.ProductService;
import com.redstar.jjd.utils.ExportUtil;
import com.redstar.jjd.vo.BankProductReturnMethodView;
import com.redstar.jjd.vo.BankProductView;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.ProductView;

/**
 * ClassName: BankController <br/>
 * Date: 2016年9月23日 下午2:05:43 <br/>
 * Description: 金融机构管理
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/admin/product.do")
public class BankProductController {
    private static final Logger LOGGER = Logger
            .getLogger(BankProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private BankService bankService;

    // @RequiresPermissions("BankProduct:view")
    @RequestMapping(params = { "method=list" })
    public String list(Model model) {
        return "bank.list";
    }

    @RequestMapping(params = { "method=query" })
    @ResponseBody
    public Map<String, Object> query(Model model, BankProductQuery query) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<BankProduct> products = new ArrayList<BankProduct>();
        products = productService.query(query);
        retMap.put("total", query.getTotalCount());
        retMap.put("rows", products);
        return retMap;
    }

    // @RequiresPermissions({ "BankProduct:add", "BankProduct:update" })
    @RequestMapping(params = { "method=save" })
    @ResponseBody
    public Map<String, Object> save(ProductView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            productService.save(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save bankProduct fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    // @RequiresPermissions("BankProduct:delete")
    @RequestMapping(params = "method=batchDel")
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        int result = 0;
        try {
            result = productService.delBatch(ids);
            if (result == -1) {
                map.put("msg", "该金融机构已有商场匹配,不能删除");
                return map;
            } else if (result == -2) {
                map.put("msg", "该金融机构已添加过利率,不能删除");
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

    @RequestMapping(params = { "method=queryRate" })
    @ResponseBody
    public Map<String, Object> queryRate(Model model, Long productId) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<BankProductReturnMethod> rates = new ArrayList<BankProductReturnMethod>();
        rates = productService.getByProductId(productId);
        retMap.put("rows", rates);
        return retMap;
    }

    @RequestMapping(params = { "method=getRateById" })
    @ResponseBody
    public BankProductReturnMethod getRateById(Model model, Long rateId) {
        BankProductReturnMethod rate = new BankProductReturnMethod();
        rate = productService.getByRateId(rateId);
        return rate;
    }

    // @RequiresPermissions({ "BankProduct:add", "BankProduct:update" })
    @RequestMapping(params = { "method=saveRate" })
    @ResponseBody
    public Map<String, Object> saveRate(BankProductReturnMethodView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            productService.saveRate(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save rate fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    // @RequiresPermissions("BankProduct:delete")
    @RequestMapping(params = "method=delRate")
    @ResponseBody
    public Map<String, Object> delRate(Long id) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        try {
            productService.delRate(id);
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
    public boolean isNameExist(String bankName, Long bankId) {
        if (null != bankId) {
            Bank bank = bankService.findBy(bankId);
            if (bankName.equals(bank.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            List<Bank> list = bankService.findByName(bankName);
            if (!list.isEmpty() && list != null) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping(params = { "method=getAllBank" })
    @ResponseBody
    public List<ComboVO> getAllBank(String hasBankPay) {
        List<Bank> list = bankService.findAll(hasBankPay);
        List<ComboVO> retBanklist = new ArrayList<ComboVO>();
        for (Entry<String, String> entry : GeneralConstant.selectMap.entrySet()) {
            ComboVO vo = new ComboVO(entry.getKey(), entry.getValue());
            retBanklist.add(vo);
        }
        for (Bank bank : list) {
            retBanklist
                    .add(new ComboVO(bank.getId().toString(), bank.getName()));
        }
        return retBanklist;
    }

    @RequestMapping(params = { "method=findBanksByStore" })
    @ResponseBody
    public List<ComboVO> findBanksByStore(Long storeId) {
        List<Bank> list = bankService.findBanksByStore(storeId);
        List<ComboVO> retBanklist = new ArrayList<ComboVO>();
        for (Entry<String, String> entry : GeneralConstant.selectMap.entrySet()) {
            ComboVO vo = new ComboVO(entry.getKey(), entry.getValue());
            retBanklist.add(vo);
        }
        for (Bank bank : list) {
            retBanklist
                    .add(new ComboVO(bank.getId().toString(), bank.getName()));
        }
        return retBanklist;
    }

    /**
     * 
     * @description 根据bankId获取productId <br/>
     * 
     * @param bankId
     * @return BankProduct
     * @throws
     */
    @RequestMapping(params = { "method=getProductByBankId" })
    @ResponseBody
    public BankProduct getProductByBankId(Long bankId) {
        BankProduct product = productService.getProductByBankId(bankId);
        return product;
    }

    /**
     * 
     * @description 金融机构导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=exportBank" })
    public void exportBank(BankProductQuery query, HttpServletRequest req,
            HttpServletResponse resp) {
        List<BankProductView> list = new ArrayList<BankProductView>();
        WritableWorkbook workbook = null;
        try {
            list = productService.queryBankExport(query);
            workbook = productService.createExcelForBank(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷_金融机构导出表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }
}
