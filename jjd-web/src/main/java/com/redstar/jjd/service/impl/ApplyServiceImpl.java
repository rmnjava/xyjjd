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
import org.springframework.transaction.annotation.Transactional;

import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.OrderBankPayDao;
import com.redstar.jjd.dao.StoreDao;
import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.dao.UserDao;
import com.redstar.jjd.dao.UserInfoDao;
import com.redstar.jjd.model.Bank;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.model.User;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.model.UserInfo;
import com.redstar.jjd.query.UserApplyQuery;
import com.redstar.jjd.service.ApplyService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.UserApplyView;

/**
 * Created by Pengfei on 2015/12/25.
 */
@Service("applyService")
@Transactional
public class ApplyServiceImpl implements ApplyService {

    public static final Logger LOGGER = Logger
            .getLogger(ApplyServiceImpl.class);

    @Autowired
    private UserApplyDao userApplyDao;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderBankPayDao orderBankPayDao;

    @Override
    public Double getSuccessAmount(String userId) {
        return userApplyDao.getAmountSumByUser(userId);
    }

    @Override
    public List<UserApplyView> query(UserApplyQuery query) {
        List<UserApplyView> result = new ArrayList<UserApplyView>();
        List<UserApply> list = userApplyDao.pageQuery(query);
        result = covertToView(list);
        return result;
    }

    @Override
    public List<UserApplyView> queryExport(UserApplyQuery query) {
        List<UserApplyView> result = new ArrayList<UserApplyView>();
        List<UserApply> list = userApplyDao.queryExport(query);
        result = covertToView(list);
        return result;
    }

    @Override
    public WritableWorkbook createExcelForNoCard(OutputStream out,
            List<UserApplyView> list) {
        String worksheet = "家居贷app_无卡用户申请信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "申请日期 ", "客户姓名", "手机号码", "身份证号", "商场名称",
                    "银行", "是否零成本", "申请金额(元)", "分期期数", "分期金额(元)", "月薪",
                    "是否有本地社保", "是否有公积金", "是否有房产", "是否有车", "是否有信用卡" };
            for (int i = 0; i < headLine.length; i++) {
                sheet.setColumnView(i, 20);
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
            String title = "app_无卡用户申请信息表";
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
            jxl.write.Label labelApplyDate;
            jxl.write.Label labelUserName;
            jxl.write.Label labelPhone;
            jxl.write.Label labelIdNumber;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelApplyAmount;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelReturnPerMonth;
            jxl.write.Label labelBankName;
            jxl.write.Label labelIsZero;
            jxl.write.Label labelIncome;
            jxl.write.Label labelHasSocialSec;
            jxl.write.Label labelHasAccumulation;
            jxl.write.Label labelHasHouse;
            jxl.write.Label labelHasCar;
            jxl.write.Label labelHasCreditCard;
            int countRow = 2;
            for (UserApplyView view : list) {
                int count = 0;
                labelApplyDate = new Label(count++, countRow,
                        view.getApplyDateFmt(), contentFormat);
                labelUserName = new Label(count++, countRow, view.getUserInfo()
                        .getName(), contentFormat);
                labelPhone = new Label(count++, countRow, view.getPhone(),
                        contentFormat);
                labelIdNumber = new Label(count++, countRow, view.getUserInfo()
                        .getIdNumber(), contentFormat);
                labelStoreName = new Label(count++, countRow,
                        view.getStoreName(), contentFormat);
                labelBankName = new Label(count++, countRow,
                        String.valueOf(view.getBankName()), contentFormat);
                String isZero = "否";
                if (view.getIsZero()) {
                    isZero = "是";
                }
                labelIsZero = new Label(count++, countRow, isZero,
                        contentFormat);
                labelApplyAmount = new Label(count++, countRow,
                        view.getAmountFmt(), contentFormat);
                labelPeriod = new Label(count++, countRow, String.valueOf(view
                        .getPeriod()), contentFormat);
                labelReturnPerMonth = new Label(count++, countRow,
                        view.getReturnPerMonthFmt(), contentFormat);
                labelIncome = new Label(count++, countRow, String.valueOf(view
                        .getUserInfo().getIncome()), contentFormat);

                String hasSocialSec = "否";
                if (view.getUserInfo().getHasSocialSec()) {
                    hasSocialSec = "是";
                }
                labelHasSocialSec = new Label(count++, countRow, hasSocialSec,
                        contentFormat);
                String hasAccumulation = "否";
                if (view.getUserInfo().getHasAccumulation()) {
                    hasAccumulation = "是";
                }
                labelHasAccumulation = new Label(count++, countRow,
                        hasAccumulation, contentFormat);
                String hasHouse = "否";
                if (view.getUserInfo().getHasHouse()) {
                    hasHouse = "是";
                }
                labelHasHouse = new Label(count++, countRow, hasHouse,
                        contentFormat);
                String hasCar = "否";
                if (view.getUserInfo().getHasCar()) {
                    hasCar = "是";
                }
                labelHasCar = new Label(count++, countRow, hasCar,
                        contentFormat);
                String hasCreditCard = "否";
                if (view.getUserInfo().getHasCreditCard()) {
                    hasCreditCard = "是";
                }
                labelHasCreditCard = new Label(count++, countRow,
                        hasCreditCard, contentFormat);
                sheet.addCell(labelApplyDate);
                sheet.addCell(labelUserName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelIdNumber);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelApplyAmount);
                sheet.addCell(labelPeriod);
                sheet.addCell(labelReturnPerMonth);
                sheet.addCell(labelBankName);
                sheet.addCell(labelIsZero);
                sheet.addCell(labelIncome);
                sheet.addCell(labelHasSocialSec);
                sheet.addCell(labelHasAccumulation);
                sheet.addCell(labelHasHouse);
                sheet.addCell(labelHasCar);
                sheet.addCell(labelHasCreditCard);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    private List<UserApplyView> covertToView(List<UserApply> list) {
        List<UserApplyView> result = new ArrayList<UserApplyView>();
        for (UserApply userApply : list) {
            UserApplyView userApplyView = new UserApplyView();
            ConvertUtils.convertObject(userApply, userApplyView);
            userApplyView.setApplyId(userApply.getId());
            UserInfo userInfo = new UserInfo();
            if (null != userApply.getUserInfoId()) {
                userInfo = userInfoDao.get(userApply.getUserInfoId());
            } else {
                userInfo = userInfoDao.findByUserId(userApply.getUserId());
            }
            userApplyView.setUserInfo(userInfo);
            if (userApply.getAmount() != null) {
                userApplyView.setAmount(userApply.getAmount() * 10000);
            }

            Long bankId = userApply.getBankId();
            if (bankId != null) {
                Bank bank = bankDao.get(bankId);
                userApplyView.setBankName(bank.getName());
            }
            Long storeId = userApply.getStoreId();
            Store store = storeDao.get(storeId);
            userApplyView.setStoreName(store.getName());

            User user = userDao.get(Long.parseLong(userApply.getUserId()));
            userApplyView.setPhone(user.getPhone());

            result.add(userApplyView);
        }
        return result;
    }
}
