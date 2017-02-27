/**
 * Project Name:jjd-weixin
 * File Name:UserPayServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年8月3日上午11:11:11
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.OrderBankPayDao;
import com.redstar.jjd.dao.UserPayDao;
import com.redstar.jjd.model.UserPay;
import com.redstar.jjd.query.UserPayQuery;
import com.redstar.jjd.service.UserPayService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.UserPayView;

/**
 * ClassName: UserPayServiceImpl <br/>
 * Date: 2016年8月3日 上午11:11:11 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("userPayService")
public class UserPayServiceImpl implements UserPayService {

    public static final Logger LOGGER = Logger
            .getLogger(UserPayServiceImpl.class);

    @Autowired
    private OrderBankPayDao orderBankPayDao;

    @Autowired
    private UserPayDao userPayDao;

    @Autowired
    private BankDao bankDao;

    @Override
    public List<UserPayView> query(UserPayQuery query) {
        List<UserPayView> viewList = new ArrayList<UserPayView>();
        List<UserPay> list = userPayDao.pageQuery(query);
        viewList = ConvertUtils.convertList(list, UserPayView.class);
        return viewList;
    }

    @Override
    public List<UserPayView> queryExport(UserPayQuery userQuery) {
        List<UserPayView> viewList = new ArrayList<UserPayView>();
        List<UserPay> list = userPayDao.queryExport(userQuery);
        viewList = ConvertUtils.convertList(list, UserPayView.class);
        return viewList;
    }

    @Override
    public WritableWorkbook createExcelForSingle(OutputStream out,
            List<UserPayView> list) {
        String worksheet = "农行单品_成功分期信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "订单号 ", "订单日期 ", "客户姓名", "手机号码", "商场名称",
                    "交易金额(元)", "分期期数", "分期金额(元)" };
            for (int i = 0; i < headLine.length; i++) {
                sheet.setColumnView(i, 30);
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
            String title = "农行单品_成功分期信息表";
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
            jxl.write.Label labelOrderNo;
            jxl.write.Label labelOrderDate;
            jxl.write.Label labelUserName;
            jxl.write.Label labelPhone;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelApplyAmount = null;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelReturnPerMonth;
            int countRow = 2;
            for (UserPayView view : list) {
                int count = 0;
                labelOrderNo = new Label(count++, countRow, view.getOrderNo(),
                        contentFormat);
                labelOrderDate = new Label(count++, countRow,
                        view.getApplyTime(), contentFormat);
                labelUserName = new Label(count++, countRow,
                        view.getUserName(), contentFormat);
                labelPhone = new Label(count++, countRow, view.getPhone(),
                        contentFormat);
                labelStoreName = new Label(count++, countRow,
                        view.getStoreName(), contentFormat);
                if (view.getAmount() != null) {
                    labelApplyAmount = new Label(count++, countRow,
                            String.valueOf(view.getAmount()), contentFormat);
                }
                labelPeriod = new Label(count++, countRow, String.valueOf(view
                        .getPeriod()), contentFormat);
                labelReturnPerMonth = new Label(count++, countRow,
                        view.getReturnPerMonthFmt(), contentFormat);
                sheet.addCell(labelOrderNo);
                sheet.addCell(labelOrderDate);
                sheet.addCell(labelUserName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelApplyAmount);
                sheet.addCell(labelPeriod);
                sheet.addCell(labelReturnPerMonth);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

}
