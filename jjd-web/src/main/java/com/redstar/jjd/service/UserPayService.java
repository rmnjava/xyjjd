/**
 * Project Name:jjd-weixin
 * File Name:UserPayService.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年8月3日上午11:06:36
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.query.UserPayQuery;
import com.redstar.jjd.vo.UserPayView;

/**
 * ClassName: UserPayService <br/>
 * Date: 2016年8月3日 上午11:06:36 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface UserPayService {

    // 查询支付成功列表
    public List<UserPayView> query(UserPayQuery query);

    // 导出支付成功列表
    public List<UserPayView> queryExport(UserPayQuery query);

    /**
     * 
     * @description 农行单品分期成功客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForSingle(OutputStream out,
            List<UserPayView> list);
}
