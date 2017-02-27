/**
 * Project Name:jjd-web
 * File Name:StoreController.java
 * Package Name:com.redstar.jjd.admin.controller
 * Date:2016年9月22日下午4:32:19
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
import com.redstar.jjd.admin.service.CityService;
import com.redstar.jjd.admin.service.ProvinceService;
import com.redstar.jjd.admin.service.StoreService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.query.StoreProductQuery;
import com.redstar.jjd.query.StoreQuery;
import com.redstar.jjd.service.BankService;
import com.redstar.jjd.service.StoreProductService;
import com.redstar.jjd.utils.ExportUtil;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.StoreProductView;
import com.redstar.jjd.vo.StoreView;

/**
 * ClassName: StoreController <br/>
 * Date: 2016年9月22日 下午4:32:19 <br/>
 * Description: 商场管理
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/admin/store.do")
public class StoreController {
    private static final Logger LOGGER = Logger
            .getLogger(StoreController.class);

    @Autowired
    private CityService cityService;
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private StoreProductService storeProductService;

    @Autowired
    private BankService bankService;

    // @RequiresPermissions("Store:view")
    @RequestMapping(params = { "method=list" })
    public String list(Model model) {
        return "store.list";
    }

    @RequestMapping(params = { "method=query" })
    @ResponseBody
    public Map<String, Object> query(Model model, StoreQuery query) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<StoreView> stores = new ArrayList<StoreView>();
        stores = storeService.query(query);
        retMap.put("total", query.getTotalCount());
        retMap.put("rows", stores);
        return retMap;
    }

    // @RequiresPermissions({ "Store:add", "Store:update" })
    @RequestMapping(params = { "method=save" })
    @ResponseBody
    public Map<String, Object> save(StoreView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            storeService.save(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save store fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    // @RequiresPermissions("Store:delete")
    @RequestMapping(params = "method=batchDel")
    @ResponseBody
    public Map<String, Object> batchDelete(String ids) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        int result = 0;
        try {
            result = storeService.delBatch(ids);
            if (result == -1) {
                map.put("msg", "该商场已匹配金融机构,不能删除");
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
    public boolean isNameExist(String storeName, Long storeId) {
        if (null != storeId) {
            Store store = storeService.findBy(storeId);
            if (storeName.equals(store.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            List<Store> list = storeService.findBy(storeName);
            if (!list.isEmpty() && list != null) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping(params = { "method=getAllProvince" })
    @ResponseBody
    public List<ComboVO> getAllProvince() {
        List<ComboVO> list = provinceService.getProvinceOptions();
        return list;
    }

    @RequestMapping(params = { "method=getCitysByProvince" })
    @ResponseBody
    public List<ComboVO> getCitysByProvince(String provinceCode) {
        List<ComboVO> list = cityService.getCitysByProvince(provinceCode);
        return list;
    }

    @RequestMapping(params = { "method=getStoresByCity" })
    @ResponseBody
    public List<ComboVO> getStoresByCity(String cityCode) {
        List<ComboVO> list = storeService.getStoresByCity(cityCode);
        return list;
    }

    @RequestMapping(params = { "method=getBanksByStore" })
    @ResponseBody
    public List<ComboVO> getBanksByStore(Long storeId) {
        List<ComboVO> list = bankService.getBanksByStoreId(storeId);
        return list;
    }

    @RequestMapping(params = { "method=queryStoreProduct" })
    @ResponseBody
    public Map<String, Object> queryStoreProduct(Model model, Long storeId) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<StoreProductView> products = new ArrayList<StoreProductView>();
        products = storeProductService.findByStoreId(storeId);
        retMap.put("rows", products);
        return retMap;
    }

    // @RequiresPermissions({ "BankProduct:add", "BankProduct:update" })
    @RequestMapping(params = { "method=saveStoreProduct" })
    @ResponseBody
    public Map<String, Object> saveStoreProduct(StoreProductView view) {
        Map<String, Object> map = Maps.newHashMap();
        String msg = "";
        Boolean success = Boolean.TRUE;
        try {
            storeProductService.save(view);
            msg = "保存成功！";
        } catch (Exception e) {
            LOGGER.error("save bank fail.", e);
            msg = "保存失败";
            success = Boolean.FALSE;
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    @RequestMapping(params = { "method=getStoreProductById" })
    @ResponseBody
    public StoreProductView getStoreProductById(Model model, Long id) {
        StoreProductView storeProduct = storeProductService.findBy(id);
        return storeProduct;
    }

    // @RequiresPermissions("BankProduct:delete")
    @RequestMapping(params = "method=delStoreProduct")
    @ResponseBody
    public Map<String, Object> delStoreProduct(Long id) {
        Map<String, Object> map = Maps.newHashMap();
        Boolean success = Boolean.TRUE;
        try {
            storeProductService.delete(id);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            success = Boolean.FALSE;
            map.put("msg", "删除失败");
        }
        map.put("success", success);
        return map;
    }

    @RequestMapping(params = { "method=getAllStore" })
    @ResponseBody
    public List<ComboVO> getAllStore() {
        List<Store> list = storeService.findAll();
        List<ComboVO> retStorelist = new ArrayList<ComboVO>();
        for (Entry<String, String> entry : GeneralConstant.selectMap.entrySet()) {
            ComboVO vo = new ComboVO(entry.getKey(), entry.getValue());
            retStorelist.add(vo);
        }
        for (Store store : list) {
            retStorelist.add(new ComboVO(store.getId().toString(), store
                    .getName()));
        }
        return retStorelist;
    }

    /**
     * 
     * @description 商场匹配金融机构导出<br/>
     * 
     * @param view
     * @param req
     * @param resp
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(params = { "method=storeBankExport" })
    public void storeBankExport(StoreProductQuery query,
            HttpServletRequest req, HttpServletResponse resp) {
        List<StoreProductView> list = new ArrayList<StoreProductView>();
        WritableWorkbook workbook = null;
        try {
            list = storeProductService.queryExportList(query);
            workbook = storeProductService.createExcelForStore(
                    resp.getOutputStream(), list);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ExportUtil.exportbyurl("家居贷_商场匹配金融机构表_" + sdf.format(new Date())
                    + ".xls", workbook, resp);
        } catch (IOException e) {
            LOGGER.info(e);
        }

    }

}
