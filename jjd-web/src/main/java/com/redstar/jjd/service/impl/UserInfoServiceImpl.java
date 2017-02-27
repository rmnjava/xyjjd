/**
 * Project Name:jjd-web
 * File Name:UserInfoServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年6月8日下午2:32:31
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;

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
import com.redstar.jjd.model.Province;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.model.User;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.model.UserInfo;
import com.redstar.jjd.query.UserQuery;
import com.redstar.jjd.query.UserWechatQuery;
import com.redstar.jjd.query.UserWechatSpecialQuery;
import com.redstar.jjd.service.UserInfoService;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserInfoView;
import com.redstar.jjd.vo.UserView;

/**
 * ClassName: UserInfoServiceImpl <br/>
 * Date: 2016年6月8日 下午2:32:31 <br/>
 * Description: userInfoService实现类
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends AbstractBasicService implements
        UserInfoService {

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

    @Override
    public User login(UserView user) throws Exception {
        User u = new User();
        // u = userDao/(username);
        // if (u == null)
        // throw new UserException("用户名错误！");
        // if (!u.getPassword().equals(MD5.GetMD5Code(password))) {
        // System.out.println(u.getPassword());
        // System.out.println(MD5.GetMD5Code(password));
        // throw new UserException("密码错误！");
        // }

        return u;
    }

    @Override
    public List<UserInfoView> query(UserQuery query) {
        List<UserInfoView> viewList = new ArrayList<UserInfoView>();
        List<Object[]> list = this.pageSQLQuery(query);
        viewList = covertToView(list);
        return viewList;
    }

    @Override
    public List<UserApplyView> queryWechat(UserWechatQuery query) {
        List<Object[]> list = this.pageSQLQuery(query);
        List<UserApplyView> viewList = covertToView2(list);
        return viewList;
    }

    @Override
    public WritableWorkbook createExcelForApp(OutputStream out,
            List<UserInfoView> list) {
        String worksheet = "app_农行成功分期信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "订单号 ", "订单日期 ", "客户姓名", "手机号码", "身份证号",
                    "商场名称", "银行", "交易金额(元)", "分期期数", "分期金额(元)" };
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
            String title = "app_农行成功分期信息表";
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
            jxl.write.Label labelIdNumber;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelBankName;
            jxl.write.Label labelApplyAmount;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelReturnPerMonth;
            int countRow = 2;
            for (UserInfoView view : list) {
                int count = 0;
                labelOrderNo = new Label(count++, countRow, view.getOrderNo(),
                        contentFormat);
                labelOrderDate = new Label(count++, countRow,
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
                labelApplyAmount = new Label(count++, countRow,
                        view.getAmount(), contentFormat);
                labelPeriod = new Label(count++, countRow, String.valueOf(view
                        .getPeriod()), contentFormat);
                labelReturnPerMonth = new Label(count++, countRow,
                        view.getReturnPerMonthFmt(), contentFormat);
                sheet.addCell(labelOrderNo);
                sheet.addCell(labelOrderDate);
                sheet.addCell(labelUserName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelIdNumber);
                sheet.addCell(labelStoreName);
                sheet.addCell(labelBankName);
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

    @Override
    public WritableWorkbook createExcelForWeixin(OutputStream out,
            List<UserApplyView> list) {
        String worksheet = "家居贷微信_农行分期信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "订单号 ", "申请日期 ", "客户姓名", "手机号码", "身份证号", "省",
                    "城市", "商场名称", "银行", "交易金额(元)", "分期期数", "分期金额(元)", "贷款是否成功",
                    "openID", "来源(红星/农行)" };
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
            String title = "微信_农行分期信息表";
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
            jxl.write.Label labelIdNumber;
            jxl.write.Label labelProvinceName;
            jxl.write.Label labelCityName;
            jxl.write.Label labelStoreName;
            jxl.write.Label labelBankName;
            jxl.write.Label labelApplyAmount;
            jxl.write.Label labelPeriod;
            jxl.write.Label labelReturnPerMonth;
            jxl.write.Label labelBankPayResult;
            jxl.write.Label labelOpenID;
            jxl.write.Label labelFrom;
            int countRow = 2;
            for (UserApplyView view : list) {
                int count = 0;
                labelOrderNo = new Label(count++, countRow, view.getOrderNo(),
                        contentFormat);
                labelOrderDate = new Label(count++, countRow,
                        view.getApplyTime(), contentFormat);
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
                labelReturnPerMonth = new Label(count++, countRow,
                        view.getReturnPerMonthFmt(), contentFormat);
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
                labelOpenID = new Label(count++, countRow, view.getOpenId(),
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
                sheet.addCell(labelReturnPerMonth);
                sheet.addCell(labelBankPayResult);
                sheet.addCell(labelOpenID);
                sheet.addCell(labelFrom);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserInfoView> queryAppExportList(UserQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t.USER_ID,a.APPLY_DATE,t.ORDER_NO,u.PHONE,a.AMOUNT*10000 as sumamount,s.NAME as storeName,");
        sql.append("a.PERIOD,a.RETURN_PER_MONTH,a.DATA_SOURCE,t.HOST_DATE,b.NAME as bankName,a.ID as applyId ");
        sql.append(" from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER u,T_STORE s ,T_BANK b where t.USER_APPLY_ID=a.ID ");
        sql.append(" and t.USER_ID=u.ID and a.STORE_ID=s.ID and a.BANK_ID=b.ID and a.DATA_SOURCE is null ");
        if (null != userQuery.getqStartTime()
                && !"".equals(userQuery.getqStartTime())) {
            queryParams.put("qStartTime", userQuery.getStartTime());
            sql.append(" and (t.ORDER_DATE >= :qStartTime) ");
        }
        if (null != userQuery.getqEndTime()
                && !"".equals(userQuery.getqEndTime())) {
            queryParams.put("qEndTime", userQuery.getEndTime());
            sql.append(" and (t.ORDER_DATE <= :qEndTime)");
        }
        sql.append(" and t.BANK_PAYRESULT=1 order by t.ORDER_DATE desc");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<UserInfoView> list = covertToView(objects);
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserApplyView> queryWechatExport(UserWechatQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select a.ID,a.APPLY_DATE,t.ORDER_NO,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH, ");
        sql.append("a.BANK_PAYRESULT,a.USER_INFO_ID,a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,t.HOST_DATE,u.ID_NUMBER ");
        sql.append(" from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER_INFO u where t.USER_APPLY_ID=a.ID ");
        sql.append(" and u.ID=a.USER_INFO_ID and a.DATA_SOURCE=2");
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
        sql.append(" order by a.APPLY_DATE desc");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<UserApplyView> list = covertToView2(objects);
        return list;
    }

    /**
     * 
     * @description object对象转为UserInfoView <br/>
     * 
     * @param list
     * @return List<UserInfoView>
     * @throws
     */
    private List<UserApplyView> covertToView2(List<Object[]> list) {
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
                    view.setIdNumber(userInfo.getIdNumber());
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

            viewList.add(view);
        }
        return viewList;

    }

    /**
     * 
     * @description object对象转为UserInfoView <br/>
     * 
     * @param list
     * @return List<UserInfoView>
     * @throws
     */
    private List<UserInfoView> covertToView(List<Object[]> list) {
        List<UserInfoView> viewList = new ArrayList<UserInfoView>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (Object[] object : list) {
            UserInfoView view = new UserInfoView();
            String applyTime = sdf.format(object[1]);
            view.setApplyTime(applyTime);
            if (object[9] != null) {
                String orderTime = sdf.format(object[9]);
                view.setOrderTime(orderTime);
            }
            view.setOrderNo(object[2] == null ? null : (String) (object[2]));
            view.setPhone(object[3] == null ? null : (String) (object[3]));
            view.setAmount(object[4] == null ? null : String.valueOf(object[4]));
            view.setStoreName(object[5] == null ? null : (String) (object[5]));
            view.setPeriod(object[6] == null ? null : Integer
                    .parseInt(object[6].toString()));
            view.setReturnPerMonth(object[7] == null ? null : Double
                    .parseDouble(object[7].toString()));
            view.setDataSource(object[8] == null ? null : (String) (object[8]));
            view.setBankName(object[10] == null ? null : (String) (object[10]));
            Long applyId = Long.parseLong(object[11].toString());
            UserApply userApply = userApplyDao.get(applyId);
            UserInfo userInfo = new UserInfo();
            if (null != userApply.getUserInfoId()) {
                userInfo = userInfoDao.get(userApply.getUserInfoId());
            } else {
                userInfo = userInfoDao.findByUserId(userApply.getUserId());
            }
            String userName = userInfo.getName();
            String idNumber = userInfo.getIdNumber();
            view.setUserName(userName);
            view.setIdNumber(idNumber);
            viewList.add(view);
        }
        return viewList;

    }

    @Override
    public List<UserApplyView> queryWechatSpecial(UserWechatSpecialQuery query) {
        List<Object[]> list = this.pageSQLQuery(query);
        List<UserApplyView> viewList = covertToViewForWechatSpecial(list);
        return viewList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserApplyView> queryWechatSpecialExport(
            UserWechatSpecialQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select a.ID,a.APPLY_DATE,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH, ");
        sql.append(" a.USER_INFO_ID,a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,u.ID_NUMBER ");
        sql.append(" from T_USER_APPLY a,T_USER_INFO u where u.ID=a.USER_INFO_ID ");
        sql.append(" and a.DATA_SOURCE=2 and a.ORDER_NO is null");

        if (null != userQuery.getPhone() && !"".equals(userQuery.getPhone())) {
            queryParams.put("phone", "%" + userQuery.getPhone() + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != userQuery.getBankId()
                && !new Long(-1L).equals(userQuery.getBankId())) {
            queryParams.put("bankId", userQuery.getBankId());
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
        sql.append(" order by a.APPLY_DATE desc");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<UserApplyView> list = covertToViewForWechatSpecial(objects);
        return list;
    }

    @Override
    public WritableWorkbook createExcelForWechatSpecial(OutputStream out,
            List<UserApplyView> list) {
        // TODO
        String worksheet = "家居贷微信_专项申请信息表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "申请日期 ", "客户姓名", "手机号码", "身份证号", "省", "城市",
                    "商场名称", "银行", "贷款金额(元)", "分期期数", "分期金额(元)", "openID",
                    "来源(红星/农行)" };
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
            String title = "家居贷微信_专项申请信息表";
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
            jxl.write.Label labelReturnPerMonth;
            jxl.write.Label labelOpenID;
            jxl.write.Label labelFrom;
            int countRow = 2;
            for (UserApplyView view : list) {
                int count = 0;
                labelOrderDate = new Label(count++, countRow,
                        view.getApplyTime(), contentFormat);
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
                labelReturnPerMonth = new Label(count++, countRow,
                        view.getReturnPerMonthFmt(), contentFormat);
                labelOpenID = new Label(count++, countRow, view.getOpenId(),
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
                sheet.addCell(labelReturnPerMonth);
                sheet.addCell(labelOpenID);
                sheet.addCell(labelFrom);
                countRow++;

            }

        } catch (Exception e) {
            LOGGER.info(e);
        }
        return workbook;
    }

    /**
     * 
     * @description object对象转为UserInfoView <br/>
     * 
     * @param list
     * @return List<UserInfoView>
     * @throws
     */
    private List<UserApplyView> covertToViewForWechatSpecial(List<Object[]> list) {
        List<UserApplyView> viewList = new ArrayList<UserApplyView>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (Object[] object : list) {
            UserApplyView view = new UserApplyView();
            String applyTime = sdf.format(object[1]);
            view.setApplyTime(applyTime);
            view.setAmount(object[2] == null ? null : Double
                    .parseDouble(object[2].toString()));
            view.setPeriod(object[3] == null ? null : Integer
                    .parseInt(object[3].toString()));
            view.setReturnPerMonth(object[4] == null ? null : Double
                    .parseDouble(object[4].toString()));
            // userInfoId
            if (object[5] != null) {
                UserInfo userInfo = userInfoDao.findUniqueBy("id",
                        Long.parseLong(object[5].toString()));
                if (userInfo != null) {
                    view.setUserName(userInfo.getName());
                    view.setPhone(userInfo.getUserPhone());
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
            view.setIdNumber(object[9] == null ? null : (String) (object[9]));
            viewList.add(view);
        }
        return viewList;

    }

}
