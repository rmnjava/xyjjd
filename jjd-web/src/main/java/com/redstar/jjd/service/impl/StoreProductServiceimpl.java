/**
 * Project Name:jjd-web
 * File Name:StoreProductServiceimpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年10月10日下午3:28:34
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.StoreProductDao;
import com.redstar.jjd.model.StoreProduct;
import com.redstar.jjd.query.StoreProductQuery;
import com.redstar.jjd.service.StoreProductService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.StoreProductView;

/**
 * ClassName: StoreProductServiceimpl <br/>
 * Date: 2016年10月10日 下午3:28:34 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("storeProductService")
public class StoreProductServiceimpl extends AbstractBasicService implements
        StoreProductService {
    public static final Logger LOGGER = Logger
            .getLogger(StoreProductServiceimpl.class);
    @Autowired
    private StoreProductDao storeProductDao;

    @Autowired
    private BankDao bankDao;

    @Override
    public List<StoreProductView> queryStoreProduct(StoreProductQuery query) {
        List<StoreProductView> viewList = new ArrayList<StoreProductView>();
        List<Object[]> list = this.pageSQLQuery(query);
        viewList = covertToView(list);
        return viewList;
    }

    private List<StoreProductView> covertToView(List<Object[]> list) {
        List<StoreProductView> viewList = new ArrayList<StoreProductView>();
        for (Object[] object : list) {
            StoreProductView view = new StoreProductView();
            view.setStoreName(object[0] == null ? null : (String) (object[0]));
            view.setBankName(object[1] == null ? null : (String) (object[1]));
            viewList.add(view);
        }
        return viewList;

    }

    @Override
    public StoreProduct save(StoreProductView view) {
        StoreProduct product = new StoreProduct();
        ConvertUtils.convertObject(view, product);
        if (view.getStoreProductId() != null
                && view.getStoreProductId() != null) {
            product.setId(view.getStoreProductId());
            return storeProductDao.update(product);
        } else {
            return storeProductDao.save(product);
        }
    }

    @Override
    public int delete(Long id) {
        if (null != id) {
            return storeProductDao.delete(id);
        }
        return 0;
    }

    @Override
    public StoreProductView findBy(Long id) {
        StoreProductView view = new StoreProductView();
        StoreProduct storeProduct = storeProductDao.findUniqueBy("id", id);
        ConvertUtils.convertObject(storeProduct, view);
        view.setStoreProductId(id);
        Object object[] = bankDao.getByProductId(storeProduct.getProductId());
        view.setBankId(Long.valueOf(object[0].toString()));
        view.setBankName((String) object[1]);
        return view;
    }

    @Override
    public List<StoreProductView> findByStoreId(Long storeId) {
        List<StoreProduct> list = storeProductDao.findBy("storeId", storeId);
        List<StoreProductView> viewList = new ArrayList<StoreProductView>();
        viewList = ConvertUtils.convertList(list, StoreProductView.class);
        for (StoreProductView view : viewList) {
            Object object[] = bankDao.getByProductId(view.getProductId());
            view.setBankId(Long.valueOf(object[0].toString()));
            view.setBankName((String) object[1]);
            for (StoreProduct product : list) {
                if (view.getProductId().equals(product.getProductId())
                        && view.getStoreId().equals(product.getStoreId())) {
                    view.setStoreProductId(product.getId());
                }

            }
        }
        return viewList;
    }

    @Override
    public List<StoreProductView> queryExportList(
            StoreProductQuery storeProductQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.name as provinceName,c.name as cityName,s.NAME as storeName,s.ADDRESS,b.NAME as bankName from T_STORE_PRODUCT t, ");
        sql.append("T_BANK_PRODUCT bp,T_STORE s,T_BANK b,T_PROVINCE p,T_CITY c ");
        sql.append("WHERE t.STORE_ID=s.ID AND t.PRODUCT_ID=bp.ID AND bp.BANK_ID=b.ID AND s.CITY_CODE=c.CODE AND c.PROVINCE_CODE=p.CODE");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.list();
        List<StoreProductView> list = covertToView2(objects);
        return list;
    }

    @Override
    public WritableWorkbook createExcelForStore(OutputStream out,
            List<StoreProductView> list) {
        String worksheet = "家居贷_商场匹配金融机构表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "省 ", "城市 ", "商场名称", "商场地址", "金融机构" };
            for (int i = 0; i < headLine.length; i++) {
                if (i == 3) {
                    sheet.setColumnView(i, 40);
                } else {
                    sheet.setColumnView(i, 20);
                }
            }
            // 标题样式
            WritableFont fontTitle = new WritableFont(
                    WritableFont.createFont("宋体"), 15, WritableFont.BOLD);
            WritableCellFormat titleFormat = new WritableCellFormat(fontTitle);
            titleFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            titleFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            sheet.mergeCells(0, 0, headLine.length - 1, 0);// (列，行，列，行)第一行合并单元格
            sheet.getSettings().setVerticalFreeze(1);// 设置冻结单元格，下拉时标题固定
            sheet.getSettings().setVerticalFreeze(2);// 设置冻结单元格，下拉时表头固定
            // 添加标题
            String title = "家居贷_商场匹配金融机构表";
            Label labelTitle = new Label(0, 0, title, titleFormat);
            sheet.addCell(labelTitle);
            // 表头样式
            WritableFont fontHeadLine = new WritableFont(
                    WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
            WritableCellFormat headLineFormat = new WritableCellFormat(
                    fontHeadLine);
            headLineFormat.setBorder(Border.TOP, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 上边框
            headLineFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 下边框
            headLineFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 右边框
            headLineFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            headLineFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            headLineFormat.setWrap(true);// 是否换行
            headLineFormat.setBackground(jxl.format.Colour.GRAY_25);// 设置背景颜色
            // 单元格内容样式
            WritableFont fontContent = new WritableFont(
                    WritableFont.createFont("宋体"), 10);
            WritableCellFormat contentFormat = new WritableCellFormat(
                    fontContent);
            contentFormat.setBorder(Border.TOP, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 上边框
            contentFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 下边框
            contentFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 右边框
            contentFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            contentFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            // 第二行开始添加
            for (int i = 0; i < headLine.length; i++) {
                Label labelHeadLine = new jxl.write.Label(i, 1, headLine[i],
                        headLineFormat);
                sheet.addCell(labelHeadLine);
            }
            jxl.write.Label labelProvinceName;
            jxl.write.Label labelCityName;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelAddress;
            jxl.write.Label labelBankName;
            int countRow = 2;
            for (StoreProductView view : list) {
                int count = 0;
                labelProvinceName = new Label(count++, countRow,
                        view.getProvinceName(), contentFormat);
                labelCityName = new Label(count++, countRow,
                        view.getCityName(), contentFormat);
                labelStoreName = new Label(count++, countRow,
                        view.getStoreName(), contentFormat);
                labelAddress = new Label(count++, countRow, view.getAddress(),
                        contentFormat);
                labelBankName = new Label(count++, countRow,
                        view.getBankName(), contentFormat);
                sheet.addCell(labelProvinceName);
                sheet.addCell(labelCityName);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelAddress);
                sheet.addCell(labelBankName);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    private List<StoreProductView> covertToView2(List<Object[]> list) {
        List<StoreProductView> viewList = new ArrayList<StoreProductView>();
        for (Object[] object : list) {
            StoreProductView view = new StoreProductView();
            view.setProvinceName(object[0] == null ? null
                    : (String) (object[0]));
            view.setCityName(object[1] == null ? null : (String) (object[1]));
            view.setStoreName(object[2] == null ? null : (String) (object[2]));
            view.setAddress(object[3] == null ? null : (String) (object[3]));
            view.setBankName(object[4] == null ? null : (String) (object[4]));
            viewList.add(view);
        }
        return viewList;

    }
}
