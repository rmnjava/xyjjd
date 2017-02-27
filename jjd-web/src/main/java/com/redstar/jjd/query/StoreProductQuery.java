/**
 * Project Name:jjd-web
 * File Name:StoreProductQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年10月10日下午3:01:49
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.util.HashMap;

/**
 * ClassName: StoreProductQuery <br/>
 * Date: 2016年10月10日 下午3:01:49 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class StoreProductQuery extends AbstractPagedQuery<Object[]> {
    /**
     * 银行id
     */
    private Long qbankId;

    private Long qStoreId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from T_STORE_PRODUCT t, T_BANK_PRODUCT p,T_STORE s,T_BANK b where t.STORE_ID=s.ID ");
            sql.append(" and t.PRODUCT_ID=p.ID and p.BANK_ID=b.ID ");
        } else {
            sql.append("select s.NAME as storeName,b.NAME as bankName from T_STORE_PRODUCT t, T_BANK_PRODUCT p,T_STORE s,T_BANK b where t.STORE_ID=s.ID ");
            sql.append(" and t.PRODUCT_ID=p.ID and p.BANK_ID=b.ID ");
        }

        if (null != qbankId && !new Long(-1L).equals(qbankId)) {
            queryParams.put("bankId", qbankId);
            sql.append(" and p.BANK_ID = :bankId ");
        }

        if (null != qStoreId && !new Long(-1L).equals(qStoreId)) {
            queryParams.put("storeId", qStoreId);
            sql.append(" and t.STORE_ID = :storeId ");
        }

        return sql.toString();
    }

    public Long getQbankId() {
        return qbankId;
    }

    public void setQbankId(Long qbankId) {
        this.qbankId = qbankId;
    }

    public Long getqStoreId() {
        return qStoreId;
    }

    public void setqStoreId(Long qStoreId) {
        this.qStoreId = qStoreId;
    }

}
