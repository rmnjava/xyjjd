/**
 * Project Name:jjd-web
 * File Name:OperatorServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年6月20日下午3:24:34
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.DeptDao;
import com.redstar.jjd.admin.dao.OperatorDao;
import com.redstar.jjd.admin.dao.RoleDao;
import com.redstar.jjd.admin.service.OperatorService;
import com.redstar.jjd.dao.CityDao;
import com.redstar.jjd.dao.StoreDao;
import com.redstar.jjd.model.City;
import com.redstar.jjd.model.Dept;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.model.UserException;
import com.redstar.jjd.query.OperatorQuery;
import com.redstar.jjd.service.impl.AbstractBasicService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.utils.MD5;
import com.redstar.jjd.vo.OperatorView;
import com.redstar.jjd.vo.StoreView;

/**
 * ClassName: OperatorServiceImpl <br/>
 * Date: 2016年6月20日 下午3:24:34 <br/>
 * Description: 登录service实现类
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("operatorService")
public class OperatorServiceImpl extends AbstractBasicService implements
        OperatorService {

    public static final Logger LOGGER = Logger
            .getLogger(OperatorServiceImpl.class);

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CityDao cityDao;

    @Override
    public Operator getOperatorByLoginName(String loginName) {
        return operatorDao.getOperatorByLoginName(loginName);
    }

    @Override
    public Operator login(OperatorView view) {
        Operator operator = new Operator();
        operator = operatorDao.getOperatorByLoginName(view.getLoginName());
        if (operator == null)
            throw new UserException("用户名错误！");
        if (!operator.getPassword().equals(MD5.GetMD5Code(view.getPassword()))) {
            throw new UserException("密码错误！");
        }
        return operator;
    }

    @Override
    public List<OperatorView> query(OperatorQuery operatorQuery) {
        List<Operator> list = operatorDao.pageQuery(operatorQuery);
        List<OperatorView> viewList = ConvertUtils.convertList(list,
                OperatorView.class);
        for (OperatorView operator : viewList) {
            if (operator.getDeptId() != null) {
                Dept dept = deptDao.get(operator.getDeptId());
                if (dept != null) {
                    operator.setDeptName(dept.getDeptName());
                }
                if (operator.getStoreId() != null) {
                    Store store = storeDao.get(operator.getStoreId());
                    StoreView view = new StoreView();
                    ConvertUtils.convertObject(store, view);
                    City city = cityDao
                            .findUniqueBy("code", view.getCityCode());
                    operator.setProvinceCode(city.getProvinceCode());
                    operator.setCityCode(city.getCode());
                }
            }

        }

        return viewList;
    }

    @Override
    public int delBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if (!id.isEmpty()) {
                // 删除用户t_sys_u2r关系记录
                operatorDao.deleteU2RByOperatorId(Long.parseLong(id));
                operatorDao.delete(Long.parseLong(id));
            }
        }
        return idArr.length;
    }

    @Override
    public void update(OperatorView view) {
        Operator operator = new Operator();
        ConvertUtils.convertObject(view, operator);
        operatorDao.update(operator);
    }

    @Override
    public Operator findById(Long operatorId) {
        return operatorDao.findUniqueBy("operatorId", operatorId);
    }

    @Override
    public Operator save(OperatorView view) {
        Operator operator = new Operator();
        ConvertUtils.convertObject(view, operator);
        operator.setCreateTime(new Timestamp(System.currentTimeMillis()));
        operator.setPassword(MD5.GetMD5Code(view.getPassword()));
        if (view.getOperatorId() != null) {
            return operatorDao.update(operator);
        } else {
            return operatorDao.save(operator);
        }

    }

    @Override
    public List<Operator> findByName(String loginName) {
        return operatorDao.findBy("loginName", loginName);
    }

    @Override
    public WritableWorkbook createExcelForOperator(OutputStream out,
            List<OperatorView> list) {
        String worksheet = "家居贷_账户导出表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "登录名", "姓名", "部门", "角色名", "手机号码", "创建时间" };
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
            String title = "家居贷_账户导出表";
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
            jxl.write.Label labelLoginName;
            jxl.write.Label labelName;
            jxl.write.Label labelDeptName;
            jxl.write.Label labelRoleName;
            jxl.write.Label labelPhone;
            jxl.write.Label labelCreateTime;
            int countRow = 2;
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            for (OperatorView view : list) {
                int count = 0;
                labelLoginName = new Label(count++, countRow,
                        view.getLoginName(), contentFormat);
                labelName = new Label(count++, countRow, String.valueOf(view
                        .getName()), contentFormat);
                labelDeptName = new Label(count++, countRow,
                        String.valueOf(view.getDeptName()), contentFormat);
                labelRoleName = new Label(count++, countRow,
                        view.getRoleNames(), contentFormat);
                labelPhone = new Label(count++, countRow, view.getPhone(),
                        contentFormat);
                String createTime = null;
                if (view.getCreateTime() != null) {
                    createTime = sdf.format(view.getCreateTime());
                }
                labelCreateTime = new Label(count++, countRow, createTime,
                        contentFormat);
                sheet.addCell(labelLoginName);
                sheet.addCell(labelName);
                sheet.addCell(labelDeptName);
                sheet.addCell(labelRoleName);
                sheet.addCell(labelPhone);
                sheet.addCell(labelCreateTime);

                countRow++;

            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
        return workbook;
    }

    @Override
    public List<OperatorView> queryExport(OperatorQuery operatorQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t from Operator t join t.roleList r where (1=1) ");
        if (StringUtils.isNotBlank(operatorQuery.getQloginName())) {
            queryParams.put("loginName", "%" + operatorQuery.getQloginName()
                    + "%");
            sql.append(" and (t.loginName like:loginName)");
        }

        if (operatorQuery.getQroleId() != null
                && 0 < operatorQuery.getQroleId()) {
            queryParams.put("roleId", operatorQuery.getQroleId());
            sql.append(" and r.roleId =:roleId ");
        }

        Query query = sessionFactory.getCurrentSession().createQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Operator> objects = query.list();
        List<OperatorView> list = ConvertUtils.convertList(objects,
                OperatorView.class);
        for (OperatorView operator : list) {
            if (operator.getDeptId() != null) {
                Dept dept = deptDao.get(operator.getDeptId());
                if (dept != null) {
                    operator.setDeptName(dept.getDeptName());
                }
            }

        }

        return list;
    }

}
