/**
 * Project Name:jjd-web
 * File Name:UserLoanService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年11月24日下午4:23:48
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.query.UserOnlineLoanQuery;
import com.redstar.jjd.query.UserSpecialLoanQuery;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserLoanView;

/**
 * ClassName: UserLoanService <br/>
 * Date: 2016年11月24日 下午4:23:48 <br/>
 * Description: 家居贷申请信息Service
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface UserLoanService {
    /**
     * 
     * @description 查询在线分期客户信息 <br/>
     * 
     * @param query
     * @return List<UserApplyView>
     * @throws
     */
    public List<UserApplyView> queryOnline(UserOnlineLoanQuery query);

    /**
     * 
     * @description 在线分期客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForOnLine(OutputStream out,
            List<UserApplyView> list);

    /**
     * 
     * @description 查询在线分期导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserInfoView>
     * @throws
     */
    public List<UserApplyView> queryOnlineExport(UserOnlineLoanQuery query);

    /**
     * 
     * @description 查询专项贷款客户信息 <br/>
     * 
     * @param query
     * @return List<UserApplyView>
     * @throws
     */
    public List<UserApplyView> querySpecial(UserSpecialLoanQuery query);

    /**
     * 
     * @description 专项贷款客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForSpecial(OutputStream out,
            List<UserApplyView> list);

    /**
     * 
     * @description 查询专项贷款导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserInfoView>
     * @throws
     */
    public List<UserApplyView> querySpecialExport(UserSpecialLoanQuery query);

    /**
     * 
     * @description 根据applyId获取专项贷款标记备注详情 <br/>
     * 
     * @param applyId
     * @return UserLoanView
     * @throws
     */
    public UserLoanView findUserLoanById(Long applyId);

    /**
     * 
     * @description 新增商场补录专项贷款客户信息<br/>
     * 
     * @param view
     * @return UserApply
     * @throws
     */
    public UserApply saveSpecialLoan(UserApplyView view);
}
