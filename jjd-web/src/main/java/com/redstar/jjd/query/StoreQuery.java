/**
 * Project Name:jjd-web
 * File Name:StoreQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年9月22日下午4:43:56
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

/**
 * ClassName: StoreQuery <br/>
 * Date: 2016年9月22日 下午4:43:56 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class StoreQuery extends AbstractPagedQuery<Object[]> {

    /**
     * 商场名称
     */
    private String storeName;

    /**
     * 商场地址
     */
    private String storeAddress;

    /**
     * 银行
     */
    private Long qbankId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(distinct(t.ID)) from T_STORE t ");
            sql.append("left join T_CITY c on  t.CITY_CODE = c.CODE left join  T_PROVINCE p on c.PROVINCE_CODE=p.CODE ");
            sql.append("left join T_STORE_PRODUCT sp on t.ID=sp.STORE_ID ");
            sql.append("left join T_BANK_PRODUCT bp on sp.PRODUCT_ID=bp.ID left join T_BANK b on b.ID=bp.BANK_ID ");
            sql.append("where 1=1 ");
        } else {
            sql.append("select distinct(t.ID),t.NAME as storeName,t.ADDRESS,c.NAME as cityName,p.NAME as provinceName,c.CODE as cityCode,p.CODE as provinceCode from T_STORE t ");
            sql.append("left join T_CITY c on  t.CITY_CODE = c.CODE left join  T_PROVINCE p on c.PROVINCE_CODE=p.CODE ");
            sql.append("left join T_STORE_PRODUCT sp on t.ID=sp.STORE_ID ");
            sql.append("left join T_BANK_PRODUCT bp on sp.PRODUCT_ID=bp.ID left join T_BANK b on b.ID=bp.BANK_ID ");
            sql.append("where 1=1 ");
        }

        if (StringUtils.isNotBlank(storeName)) {
            queryParams.put("name", "%" + storeName + "%");
            sql.append(" and (t.NAME like :name)");
        }

        if (StringUtils.isNotBlank(storeAddress)) {
            queryParams.put("address", "%" + storeAddress + "%");
            sql.append(" and (t.ADDRESS like :address)");
        }

        if (null != qbankId && !new Long(-1L).equals(qbankId)) {
            queryParams.put("bankId", qbankId);
            sql.append(" and b.ID = :bankId ");
        }
        return sql.toString();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Long getQbankId() {
        return qbankId;
    }

    public void setQbankId(Long qbankId) {
        this.qbankId = qbankId;
    }

}
