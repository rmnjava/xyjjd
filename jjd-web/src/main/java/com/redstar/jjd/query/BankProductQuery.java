/**
 * Project Name:jjd-web
 * File Name:BankProductQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年9月23日下午2:32:43
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.util.HashMap;

import com.redstar.jjd.model.BankProduct;

/**
 * ClassName: BankProductQuery <br/>
 * Date: 2016年9月23日 下午2:32:43 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class BankProductQuery extends AbstractPagedQuery<BankProduct> {

    /**
     * 银行id
     */
    private Long qbankId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from BankProduct t where 1=1 ");
        } else {
            sql.append("select t from BankProduct t where 1=1 ");
        }

        if (null != qbankId && !new Long(-1L).equals(qbankId)) {
            queryParams.put("bankId", qbankId);
            sql.append(" and t.bankId = :bankId ");
        }

        return sql.toString();
    }

    public Long getQbankId() {
        return qbankId;
    }

    public void setQbankId(Long qbankId) {
        this.qbankId = qbankId;
    }

}
