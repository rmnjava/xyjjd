/**
 * Project Name:jjd-web
 * File Name:UserLoanServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年11月24日下午4:24:02
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jodd.util.StringUtil;
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
import org.springframework.transaction.annotation.Transactional;

import com.redstar.jjd.admin.dao.DeptDao;
import com.redstar.jjd.admin.dao.OperatorDao;
import com.redstar.jjd.admin.service.UserLoanService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.CityDao;
import com.redstar.jjd.dao.ProvinceDao;
import com.redstar.jjd.dao.StoreDao;
import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.dao.UserDao;
import com.redstar.jjd.dao.UserInfoDao;
import com.redstar.jjd.model.Bank;
import com.redstar.jjd.model.City;
import com.redstar.jjd.model.Dept;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Province;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.model.User;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.model.UserInfo;
import com.redstar.jjd.query.UserOnlineLoanQuery;
import com.redstar.jjd.query.UserSpecialLoanQuery;
import com.redstar.jjd.service.impl.AbstractBasicService;
import com.redstar.jjd.service.impl.UserInfoServiceImpl;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.utils.IdcardUtils;
import com.redstar.jjd.vo.LoanExamineView;
import com.redstar.jjd.vo.LoanIntentionView;
import com.redstar.jjd.vo.LoanIntoView;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserLoanView;

/**
 * ClassName: UserLoanServiceImpl <br/>
 * Date: 2016年11月24日 下午4:24:02 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("userLoanService")
@Transactional
public class UserLoanServiceImpl extends AbstractBasicService implements
        UserLoanService {

    public static final Logger LOGGER = Logger
            .getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserApplyDao userApplyDao;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public WritableWorkbook createExcelForOnLine(OutputStream out,
            List<UserApplyView> list) {
        String worksheet = "家居贷_在线持卡分期信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "订单号 ", "申请日期 ", "交易日期 ", "客户姓名", "手机号码",
                    "身份证号", "省", "城市", "商场名称", "银行", "交易金额(元)", "分期期数",
                    "贷款是否成功", "渠道", "来源(红星/农行)" };
            for (int i = 0; i < headLine.length; i++) {
                if (i == 11) {
                    sheet.setColumnView(i, 30);
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
            String title = "家居贷_在线持卡分期信息表";
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
            jxl.write.Label labelApplyDate;
            jxl.write.Label labelOrderDate;
            jxl.write.Label labelUserName;
            jxl.write.Label labelPhone;
            jxl.write.Label labelIdNumber;
            jxl.write.Label labelProvinceName;
            jxl.write.Label labelCityName;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelBankName;
            jxl.write.Label labelApplyAmount;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelBankPayResult;
            jxl.write.Label labelDataSource;
            jxl.write.Label labelFrom;
            int countRow = 2;
            for (UserApplyView view : list) {
                int count = 0;
                labelOrderNo = new Label(count++, countRow, view.getOrderNo(),
                        contentFormat);
                labelApplyDate = new Label(count++, countRow,
                        view.getApplyTime(), contentFormat);
                labelOrderDate = new Label(count++, countRow,
                        view.getOrderTime(), contentFormat);
                labelUserName = new Label(count++, countRow,
                        view.getUserName(), contentFormat);
                labelPhone = new Label(count++, countRow, view.getPhone(),
                        contentFormat);
                labelIdNumber = new Label(count++, countRow,
                        view.getIdNumber(), contentFormat);
                labelProvinceName = new Label(count++, countRow,
                        view.getProvinceName(), contentFormat);
                labelCityName = new Label(count++, countRow,
                        view.getCityName(), contentFormat);
                labelStoreName = new Label(count++, countRow,
                        view.getStoreName(), contentFormat);
                labelBankName = new Label(count++, countRow,
                        view.getBankName(), contentFormat);
                labelApplyAmount = new Label(count++, countRow,
                        String.valueOf(view.getAmount()), contentFormat);
                labelPeriod = new Label(count++, countRow, String.valueOf(view
                        .getPeriod()), contentFormat);
                String bankPayResult = view.getBankPayResult();
                // 贷款成功
                if (bankPayResult
                        .equals(GeneralConstant.BANK_PAY_RESULT_SUCCESS)) {
                    bankPayResult = "是";
                } else {
                    bankPayResult = "否";
                }
                labelBankPayResult = new Label(count++, countRow,
                        bankPayResult, contentFormat);
                String dataSourceStr = "";
                if (view.getDataSource() != null) {
                    if (view.getDataSource().equals("")) {
                        dataSourceStr = "APP";
                    } else if (view.getDataSource().equals("2")) {
                        dataSourceStr = "微信";
                    } else if (view.getDataSource().equals("3")) {
                        dataSourceStr = "H5";
                    } else
                        dataSourceStr = "PC";
                }
                labelDataSource = new Label(count++, countRow, dataSourceStr,
                        contentFormat);
                String fromStr = "";
                if (view.getSourceFrom() != null) {
                    if (view.getSourceFrom().equals("0")) {
                        fromStr = "红星";
                    }
                    if (view.getSourceFrom().equals("1")) {
                        fromStr = "农行";
                    }
                }
                labelFrom = new Label(count++, countRow, fromStr, contentFormat);
                sheet.addCell(labelOrderNo);
                sheet.addCell(labelApplyDate);
                sheet.addCell(labelOrderDate);
                sheet.addCell(labelUserName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelIdNumber);
                sheet.addCell(labelProvinceName);
                sheet.addCell(labelCityName);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelBankName);
                sheet.addCell(labelApplyAmount);
                sheet.addCell(labelPeriod);
                sheet.addCell(labelBankPayResult);
                sheet.addCell(labelDataSource);
                sheet.addCell(labelFrom);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    @Override
    public List<UserApplyView> queryOnline(UserOnlineLoanQuery query) {
        List<Object[]> list = this.pageSQLQuery(query);
        List<UserApplyView> viewList = covertToView2(list, query.getRoleName());
        return viewList;
    }

    @Override
    public List<UserApplyView> queryOnlineExport(UserOnlineLoanQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select a.ID,a.APPLY_DATE,t.ORDER_NO,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH, ");
        sql.append("a.BANK_PAYRESULT,a.USER_INFO_ID,a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,t.HOST_DATE,a.DATA_SOURCE ");
        sql.append(" from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER_INFO u where t.USER_APPLY_ID=a.ID ");
        sql.append(" and u.ID=a.USER_INFO_ID ");
        if (null != userQuery.getOrderNo()
                && !"".equals(userQuery.getOrderNo())) {
            queryParams.put("orderNo", "%" + userQuery.getOrderNo() + "%");
            sql.append(" and a.ORDER_NO like :orderNo ");
        }

        if (null != userQuery.getPhone() && !"".equals(userQuery.getPhone())) {
            queryParams.put("phone", "%" + userQuery.getPhone() + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != userQuery.getBankId()
                && !new Long(-1L).equals(userQuery.getBankId())) {
            queryParams.put("bankId", userQuery.getBankId());
            sql.append(" and a.BANK_ID = :bankId ");
        }

        if (null != userQuery.getBankPayResult()
                && !"".equals(userQuery.getBankPayResult())) {
            queryParams.put("bankPayResult", userQuery.getBankPayResult());
            sql.append(" and a.BANK_PAYRESULT =:bankPayResult ");
        }

        if (null != userQuery.getStartTime()
                && !"".equals(userQuery.getStartTime())) {
            queryParams.put("qStartTime", userQuery.getStartTime());
            sql.append(" and (a.APPLY_DATE >= :qStartTime) ");
        }
        if (null != userQuery.getEndTime()
                && !"".equals(userQuery.getEndTime())) {
            queryParams.put("qEndTime", userQuery.getEndTime());
            sql.append(" and (a.APPLY_DATE <= :qEndTime)");
        }
        if (null != userQuery.getStoreId()) {
            queryParams.put("storeId", userQuery.getStoreId());
            sql.append(" and a.STORE_ID = :storeId ");
        }
        if (null != userQuery.getDataSource()
                && !("-1").equals(userQuery.getDataSource())) {
            queryParams.put("dataSource", userQuery.getDataSource());
            sql.append(" and a.DATA_SOURCE = :dataSource ");
        }
        sql.append(" order by a.APPLY_DATE desc");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<UserApplyView> list = covertToView2(objects,
                userQuery.getRoleName());
        return list;
    }

    /**
     * 
     * @description object对象转为UserApplyView <br/>
     * 
     * @param list
     * @return List<UserApplyView>
     * @throws
     */
    private List<UserApplyView> covertToView2(List<Object[]> list,
            String roleName) {
        List<UserApplyView> viewList = new ArrayList<UserApplyView>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (Object[] object : list) {
            UserApplyView view = new UserApplyView();
            String applyTime = sdf.format(object[1]);
            view.setApplyTime(applyTime);
            if (object[11] != null) {
                String orderTime = sdf.format(object[11]);
                view.setOrderTime(orderTime);
            }
            view.setOrderNo(object[2] == null ? null : (String) (object[2]));
            view.setAmount(object[3] == null ? null : Double
                    .parseDouble(object[3].toString()));
            view.setPeriod(object[4] == null ? null : Integer
                    .parseInt(object[4].toString()));
            view.setReturnPerMonth(object[5] == null ? null : Double
                    .parseDouble(object[5].toString()));
            view.setBankPayResult(object[6] == null ? null
                    : (String) (object[6]));
            UserInfo userInfo = new UserInfo();
            // userInfoId
            if (object[7] != null) {
                userInfo = userInfoDao.findUniqueBy("id",
                        Long.parseLong(object[7].toString()));
                if (userInfo != null) {
                    view.setUserName(userInfo.getName());
                    view.setPhone(userInfo.getUserPhone());
                    String idNumber = "";
                    if (!StringUtil.isEmpty(roleName)
                            && (roleName.trim().equals(
                                    GeneralConstant.ROLENAME_1)
                                    || (roleName.trim()
                                            .equals(GeneralConstant.STOREROLENAME_2)) || (roleName
                                        .trim()
                                    .equals(GeneralConstant.ROLENAME_2)))) {
                        idNumber = userInfo.getIdNumber();
                    } else {
                        idNumber = IdcardUtils.hideNumber(userInfo
                                .getIdNumber());
                    }
                    view.setIdNumber(idNumber);
                    User user = userDao
                            .get(Long.parseLong(userInfo.getUserId()));
                    if (user != null) {
                        view.setOpenId(user.getOpenId());
                    }
                }
            }
            // bankId
            if (object[8] != null) {
                Bank bank = bankDao.get(Long.parseLong(object[8].toString()));
                view.setBankName(bank.getName());
            }
            // storeId
            if (object[9] != null) {
                Store store = storeDao
                        .get(Long.parseLong(object[9].toString()));
                view.setStoreName(store.getName());
                City city = cityDao.findUniqueBy("code", store.getCityCode());
                view.setCityName(city.getName());
                Province p = provinceDao.findUniqueBy("code",
                        city.getProvinceCode());
                view.setProvinceName(p.getName());
            }
            view.setSourceFrom(object[10] == null ? null
                    : (String) (object[10]));
            view.setDataSource(object[12] == null ? null
                    : (String) (object[12]));
            viewList.add(view);
        }
        return viewList;

    }

    @Override
    public List<UserApplyView> querySpecial(UserSpecialLoanQuery query) {
        List<Object[]> list = this.pageSQLQuery(query);
        List<UserApplyView> viewList = covertToView(list, query.getRoleName());
        return viewList;
    }

    @Override
    public WritableWorkbook createExcelForSpecial(OutputStream out,
            List<UserApplyView> list) {
        String worksheet = "家居贷_专项申请信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "申请时间 ", "客户姓名", "手机号码", "身份证号", "商场名称",
                    "申请贷款银行", "申请贷款金额(元)", "申请贷款期数", "是否有贷款意向", "是否已进件",
                    "审批是否通过", "渠道", "补录人", "补录时间", "来源(红星/农行)" };
            for (int i = 0; i < headLine.length; i++) {
                if (i == 11) {
                    sheet.setColumnView(i, 30);
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
            String title = "家居贷_专项申请信息表";
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
            jxl.write.Label labelBankName;
            jxl.write.Label labelApplyAmount;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelIsLoan;
            jxl.write.Label labelIsInto;
            jxl.write.Label labelIsLoanSuccess;
            jxl.write.Label labelDataSource;
            jxl.write.Label labelFrom;
            jxl.write.Label labelOperatorName;
            jxl.write.Label labelOperatorTime;
            int countRow = 2;
            for (UserApplyView view : list) {
                int count = 0;
                labelApplyDate = new Label(count++, countRow,
                        view.getApplyTime(), contentFormat);
                labelUserName = new Label(count++, countRow,
                        view.getUserName(), contentFormat);
                labelPhone = new Label(count++, countRow, view.getPhone(),
                        contentFormat);
                labelIdNumber = new Label(count++, countRow,
                        view.getIdNumber(), contentFormat);
                labelStoreName = new Label(count++, countRow,
                        view.getStoreName(), contentFormat);
                labelBankName = new Label(count++, countRow,
                        view.getBankName(), contentFormat);
                String amountStr = "";
                if (view.getAmount() != null) {
                    amountStr = String.valueOf(view.getAmount());
                }
                labelApplyAmount = new Label(count++, countRow, amountStr,
                        contentFormat);
                String periodStr = "";
                if (view.getPeriod() != null) {
                    periodStr = String.valueOf(view.getPeriod());
                }
                labelPeriod = new Label(count++, countRow, periodStr,
                        contentFormat);
                String isLoan = "/";
                if (view.getIsLoan() != null) {
                    if (view.getIsLoan()) {
                        isLoan = "是";
                    } else {
                        isLoan = "否";
                    }
                }
                labelIsLoan = new Label(count++, countRow, isLoan,
                        contentFormat);
                String isInto = "/";
                if (view.getIsInto() != null) {
                    if (view.getIsInto()) {
                        isInto = "是";
                    } else {
                        isInto = "否";
                    }
                }
                labelIsInto = new Label(count++, countRow, isInto,
                        contentFormat);
                String isLoanSuccess = "/";
                if (view.getIsLoanSuccess() != null) {
                    if (view.getIsLoanSuccess()) {
                        isLoanSuccess = "是";
                    } else {
                        isLoanSuccess = "否";
                    }
                }
                labelIsLoanSuccess = new Label(count++, countRow,
                        isLoanSuccess, contentFormat);
                String dataSourceStr = "";
                if (view.getDataSource() != null) {
                    if (view.getDataSource().equals("")) {
                        dataSourceStr = "APP";
                    } else if (view.getDataSource().equals("2")) {
                        dataSourceStr = "微信";
                    } else if (view.getDataSource().equals("3")) {
                        dataSourceStr = "H5";
                    } else if (view.getDataSource().equals("4")) {
                        dataSourceStr = "PC";
                    } else if (view.getDataSource().equals("5")) {
                        dataSourceStr = "商场补录";
                    }

                }
                labelDataSource = new Label(count++, countRow, dataSourceStr,
                        contentFormat);
                String fromStr = "";
                if (view.getSourceFrom() != null) {
                    if (view.getSourceFrom().equals("0")) {
                        fromStr = "红星";
                    }
                    if (view.getSourceFrom().equals("1")) {
                        fromStr = "农行";
                    }
                }
                labelOperatorName = new Label(count++, countRow,
                        view.getOperatorName(), contentFormat);
                labelOperatorTime = new Label(count++, countRow,
                        view.getOperatorTimeStr(), contentFormat);
                labelFrom = new Label(count++, countRow, fromStr, contentFormat);
                sheet.addCell(labelApplyDate);
                sheet.addCell(labelUserName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelIdNumber);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelBankName);
                sheet.addCell(labelApplyAmount);
                sheet.addCell(labelPeriod);
                sheet.addCell(labelIsLoan);
                sheet.addCell(labelIsInto);
                sheet.addCell(labelIsLoanSuccess);
                sheet.addCell(labelDataSource);
                sheet.addCell(labelOperatorName);
                sheet.addCell(labelOperatorTime);
                sheet.addCell(labelFrom);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    @Override
    public List<UserApplyView> querySpecialExport(UserSpecialLoanQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct(a.ID),a.APPLY_DATE,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH,a.USER_INFO_ID, ");
        sql.append("a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,a.DATA_SOURCE,li.IS_LOAN,it.IS_INTO,le.IS_LOAN_SUCCESS,a.OPERATOR_ID,a.OPERATOR_TIME ");
        sql.append("from T_USER_APPLY a left join T_USER_INFO u on u.ID=a.USER_INFO_ID ");
        sql.append("left join T_LOAN_INTENTION li on a.ID=li.APPLY_ID ");
        sql.append("left join T_LOAN_INTO it on a.ID=it.APPLY_ID ");
        sql.append("left join T_LOAN_EXAMINE le on a.ID=le.APPLY_ID ");
        sql.append("where a.ORDER_NO is null ");
        if (null != userQuery.getPhone() && !"".equals(userQuery.getPhone())) {
            queryParams.put("phone", "%" + userQuery.getPhone() + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != userQuery.getQbankId()
                && !new Long(-1L).equals(userQuery.getQbankId())) {
            queryParams.put("bankId", userQuery.getQbankId());
            sql.append(" and a.BANK_ID = :bankId ");
        }

        if (null != userQuery.getStartTime()
                && !"".equals(userQuery.getStartTime())) {
            queryParams.put("qStartTime", userQuery.getStartTime());
            sql.append(" and (a.APPLY_DATE >= :qStartTime) ");
        }
        if (null != userQuery.getEndTime()
                && !"".equals(userQuery.getEndTime())) {
            queryParams.put("qEndTime", userQuery.getEndTime());
            sql.append(" and (a.APPLY_DATE <= :qEndTime)");
        }
        if (null != userQuery.getStoreId()) {
            queryParams.put("storeId", userQuery.getStoreId());
            sql.append(" and a.STORE_ID = :storeId ");
        }
        if (null != userQuery.getDataSource()
                && !("-1").equals(userQuery.getDataSource())) {
            queryParams.put("dataSource", userQuery.getDataSource());
            sql.append(" and a.DATA_SOURCE = :dataSource ");
        }

        if (null != userQuery.getIsLoan() && !"".equals(userQuery.getIsLoan())) {
            queryParams.put("isLoan", userQuery.getIsLoan());
            sql.append(" and li.IS_LOAN = :isLoan ");
        }

        if (null != userQuery.getIsInto() && !"".equals(userQuery.getIsInto())) {
            queryParams.put("isInto", userQuery.getIsInto());
            sql.append(" and it.IS_INTO = :isInto ");
        }

        if (null != userQuery.getIsLoanSuccess()
                && !"".equals(userQuery.getIsLoanSuccess())) {
            queryParams.put("isLoanSuccess", userQuery.getIsLoanSuccess());
            sql.append(" and le.IS_LOAN_SUCCESS = :isLoanSuccess ");
        }
        if (null != userQuery.getqCustomerType()
                && !"-1".equals(userQuery.getqCustomerType())) {
            queryParams.put("qCustomerType", userQuery.getqCustomerType());
            sql.append(" and li.CUSTOMER_TYPE = :qCustomerType ");
        }
        sql.append(" order by a.APPLY_DATE desc");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<UserApplyView> list = covertToView(objects,
                userQuery.getRoleName());
        return list;
    }

    /**
     * 
     * @description object对象转为UserApplyView <br/>
     * 
     * @param list
     * @return List<UserApplyView>
     * @throws
     */
    private List<UserApplyView> covertToView(List<Object[]> list,
            String roleName) {
        List<UserApplyView> viewList = new ArrayList<UserApplyView>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (Object[] object : list) {
            UserApplyView view = new UserApplyView();
            String applyTime = sdf.format(object[1]);
            view.setApplyTime(applyTime);
            if (object[2] != null) {
                view.setAmount(Double.parseDouble(object[2].toString()));
            }
            if (object[3] != null) {
                view.setPeriod(Integer.parseInt(object[3].toString()));
            }
            if (object[4] != null) {
                view.setReturnPerMonth(Double.parseDouble(object[4].toString()));
            }
            UserInfo userInfo = new UserInfo();
            // userInfoId
            if (object[5] != null) {
                userInfo = userInfoDao.findUniqueBy("id",
                        Long.parseLong(object[5].toString()));
                if (userInfo != null) {
                    view.setUserName(userInfo.getName());
                    view.setPhone(userInfo.getUserPhone());
                    String idNumber = "";
                    if (!StringUtil.isEmpty(roleName)
                            && (roleName.trim().equals(
                                    GeneralConstant.ROLENAME_1)
                                    || (roleName.trim()
                                            .equals(GeneralConstant.STOREROLENAME_2)) || (roleName
                                        .trim()
                                    .equals(GeneralConstant.ROLENAME_2)))) {
                        idNumber = userInfo.getIdNumber();
                    } else {
                        idNumber = IdcardUtils.hideNumber(userInfo
                                .getIdNumber());
                    }
                    view.setIdNumber(idNumber);
                    User user = userDao
                            .get(Long.parseLong(userInfo.getUserId()));
                    if (user != null) {
                        view.setOpenId(user.getOpenId());
                    }
                }
            }
            // bankId
            if (object[6] != null) {
                Bank bank = bankDao.get(Long.parseLong(object[6].toString()));
                view.setBankName(bank.getName());
            }
            // storeId
            if (object[7] != null) {
                Store store = storeDao
                        .get(Long.parseLong(object[7].toString()));
                view.setStoreName(store.getName());
                City city = cityDao.findUniqueBy("code", store.getCityCode());
                view.setCityName(city.getName());
                Province p = provinceDao.findUniqueBy("code",
                        city.getProvinceCode());
                view.setProvinceName(p.getName());
            }
            view.setSourceFrom(object[8] == null ? null : (String) (object[8]));
            view.setDataSource(object[9] == null ? null : (String) (object[9]));
            view.setApplyId(object[0] == null ? null : Long.parseLong(object[0]
                    .toString()));
            UserApply userApply = userApplyDao.get(view.getApplyId());
            view.setIsLoan(userApply.getIsLoan());
            view.setIsInto(userApply.getIsInto());
            view.setIsLoanSuccess(userApply.getIsLoanSuccess());
            if (object[13] != null) {
                Operator operator = operatorDao.findUniqueBy("operatorId",
                        Long.parseLong(object[13].toString()));
                view.setOperatorName(operator.getName());
            }
            if (object[14] != null) {
                String operatorTimeStr = sdf.format(object[14]);
                view.setOperatorTimeStr(operatorTimeStr);
            }

            viewList.add(view);
        }
        return viewList;

    }

    @Override
    public UserLoanView findUserLoanById(Long applyId) {
        Object[] object = userApplyDao.getUserLoanById(applyId);
        UserLoanView view = covertToView3(object);
        return view;
    }

    /**
     * 
     * @description object对象转为UserLoanView <br/>
     * 
     * @param object
     * @return UserLoanView
     * @throws
     */
    private UserLoanView covertToView3(Object[] object) {
        UserLoanView view = new UserLoanView();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        view.setApplyId(object[0] == null ? null : Long.parseLong(object[0]
                .toString()));
        // 贷款意向view
        LoanIntentionView loanIntentionView = new LoanIntentionView();
        Boolean isLoan = null;
        if (object[1] != null) {
            if (object[1].equals('1')) {
                isLoan = true;
            } else {
                isLoan = false;
            }
        }
        loanIntentionView.setIsLoan(isLoan);
        if (object[2] != null) {
            String operatorTime = sdf.format(object[2]);
            loanIntentionView.setOperatorTime(operatorTime);
        }
        if (object[3] != null) {
            loanIntentionView
                    .setOperatorId(Long.parseLong(object[3].toString()));
            Operator operator = operatorDao.findUniqueBy("operatorId",
                    Long.parseLong(object[3].toString()));
            loanIntentionView.setOperatorName(operator.getName());
            Dept dept = deptDao.get(operator.getDeptId());
            loanIntentionView.setDeptName(dept.getDeptName());
        }
        loanIntentionView.setNote(object[4] == null ? null : object[4]
                .toString());
        loanIntentionView.setCustomerType(object[13] == null ? null
                : object[13].toString());
        view.setLoanIntentionView(loanIntentionView);
        // 进件view
        LoanIntoView loanIntoView = new LoanIntoView();
        Boolean isInto = null;
        if (object[5] != null) {
            if (object[5].equals('1')) {
                isInto = true;
            } else {
                isInto = false;
            }
        }
        loanIntoView.setIsInto(isInto);
        if (object[6] != null) {
            String operatorTime = sdf.format(object[6]);
            loanIntoView.setOperatorTime(operatorTime);
        }
        if (object[7] != null) {
            loanIntoView.setOperatorId(Long.parseLong(object[7].toString()));
            Operator operator = operatorDao.findUniqueBy("operatorId",
                    Long.parseLong(object[7].toString()));
            loanIntoView.setOperatorName(operator.getName());
            Dept dept = deptDao.get(operator.getDeptId());
            loanIntoView.setDeptName(dept.getDeptName());
        }
        loanIntoView.setNote(object[8] == null ? null : object[8].toString());
        if (object[14] != null) {
            String intoTime = sdf2.format(object[14]);
            loanIntoView.setIntoTime(intoTime);
        }
        view.setLoanIntoView(loanIntoView);
        // 贷款审批是否通过view
        LoanExamineView loanExamineView = new LoanExamineView();
        Boolean isExamine = null;
        if (object[9] != null) {
            if (object[9].equals('1')) {
                isExamine = true;
            } else {
                isExamine = false;
            }
        }
        loanExamineView.setIsLoanSuccess(isExamine);
        if (object[10] != null) {
            String operatorTime = sdf.format(object[10]);
            loanExamineView.setOperatorTime(operatorTime);
        }
        if (object[11] != null) {
            loanIntoView.setOperatorId(Long.parseLong(object[11].toString()));
            Operator operator = operatorDao.findUniqueBy("operatorId",
                    Long.parseLong(object[11].toString()));
            loanExamineView.setOperatorName(operator.getName());
            Dept dept = deptDao.get(operator.getDeptId());
            loanExamineView.setDeptName(dept.getDeptName());
        }
        loanExamineView.setNote(object[12] == null ? null : object[12]
                .toString());
        if (object[15] != null) {
            String arrivalTime = sdf2.format(object[15]);
            loanExamineView.setArrivalTime(arrivalTime);
        }
        view.setLoanExamineView(loanExamineView);
        return view;

    }

    @Override
    public UserApply saveSpecialLoan(UserApplyView view) {
        UserApply userApply = new UserApply();
        // 保存user信息
        User saveUser = new User();
        User userFromDb = userDao.findByPhoneAndChannel(view.getPhone(),
                GeneralConstant.DATA_SOURCE_STORE);
        if (userFromDb == null || userFromDb.equals("")) {
            User user = new User();
            user.setPhone(view.getPhone());
            user.setEnterChannel(GeneralConstant.DATA_SOURCE_STORE);
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            saveUser = userDao.save(user);
        } else {
            ConvertUtils.convertObject(userFromDb, saveUser);
        }
        // 保存userInfo信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(saveUser.getId()));
        userInfo.setUserPhone(view.getPhone());
        userInfo.setIdNumber(view.getIdNumber());
        userInfo.setName(view.getUserName());
        userInfoDao.save(userInfo);
        // 保存订单信息
        ConvertUtils.convertObject(view, userApply);
        userApply.setOperatorTime(new Date(System.currentTimeMillis()));
        userApply.setApplyStatus(0);
        userApply.setUserInfoId(userInfo.getId());
        userApply.setUserId(String.valueOf(saveUser.getId()));
        userApply.setDataSource(GeneralConstant.DATA_SOURCE_STORE);
        return userApplyDao.save(userApply);
    }
}
