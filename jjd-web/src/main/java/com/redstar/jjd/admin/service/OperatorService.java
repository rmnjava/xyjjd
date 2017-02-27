/**
 * Project Name:jjd-web
 * File Name:UserInfo.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年6月8日下午2:31:33
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.model.Operator;
import com.redstar.jjd.query.OperatorQuery;
import com.redstar.jjd.vo.OperatorView;

/**
 * ClassName: UserInfo <br/>
 * Date: 2016年6月8日 下午2:31:33 <br/>
 * Description: 操作员service
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface OperatorService {

    public Operator getOperatorByLoginName(String loginName);

    public Operator findById(Long operatorId);

    // 登录
    public Operator login(OperatorView view);

    public List<OperatorView> query(OperatorQuery operatorQuery);

    /**
     * 
     * @description 账户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForOperator(OutputStream out,
            List<OperatorView> list);

    /**
     * 
     * @description 账户导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<OperatorView>
     * @throws
     */
    public List<OperatorView> queryExport(OperatorQuery query);

    public void update(OperatorView view);

    public Operator save(OperatorView view);

    public int delBatch(String ids);

    public List<Operator> findByName(String loginName);
}
