/**
 * Project Name:jjd-web
 * File Name:UserWechatSpecialQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年10月12日下午5:19:18
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.utils.UtilDatetime;

/**
 * ClassName: UserWechatSpecialQuery <br/>
 * Date: 2016年10月12日 下午5:19:18 <br/>
 * Description: 微信专项申请查询
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserWechatSpecialQuery extends AbstractPagedQuery<Object[]> {

    /**
     * 开始时间
     */
    private String qStartTime;

    /**
     * 结束时间
     */
    private String qEndTime;

    private Long bankId;

    private String phone;

    private Long storeId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from T_USER_APPLY a,T_USER_INFO u where u.ID=a.USER_INFO_ID ");
            sql.append(" and a.DATA_SOURCE=2 and a.ORDER_NO is null");
        } else {
            sql.append("select a.ID,a.APPLY_DATE,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH, ");
            sql.append(" a.USER_INFO_ID,a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,u.ID_NUMBER ");
            sql.append(" from T_USER_APPLY a,T_USER_INFO u where u.ID=a.USER_INFO_ID ");
            sql.append(" and a.DATA_SOURCE=2 and a.ORDER_NO is null");
        }

        if (null != phone && !"".equals(phone)) {
            queryParams.put("phone", "%" + phone + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != bankId && !new Long(-1L).equals(bankId)) {
            queryParams.put("bankId", bankId);
            sql.append(" and a.BANK_ID = :bankId ");
        }

        if (null != qStartTime && !"".equals(qStartTime)) {
            queryParams.put("qStartTime", getStartTime());
            sql.append(" and (a.APPLY_DATE >= :qStartTime) ");
        }
        if (null != qEndTime && !"".equals(qEndTime)) {
            queryParams.put("qEndTime", getEndTime());
            sql.append(" and (a.APPLY_DATE <= :qEndTime)");
        }
        if (null != storeId) {
            queryParams.put("storeId", storeId);
            sql.append(" and a.STORE_ID = :storeId ");
        }
        sql.append(" order by a.APPLY_DATE desc");
        return sql.toString();
    }

    public Timestamp getStartTime() {
        Timestamp startTime = null;
        if (null != qStartTime && !"".equals(qStartTime)) {
            try {
                startTime = new Timestamp(UtilDatetime.getStartTimeByDay(
                        UtilDatetime.parseStringToShortDate(qStartTime))
                        .getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return startTime;
    }

    public Timestamp getEndTime() {
        Timestamp endTime = null;
        if (null != qEndTime && !"".equals(qEndTime)) {
            try {
                endTime = new Timestamp(UtilDatetime.getEndTimeByDay(
                        UtilDatetime.parseStringToShortDate(qEndTime))
                        .getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return endTime;
    }

    public String getqStartTime() {
        return qStartTime;
    }

    public void setqStartTime(String qStartTime) {
        this.qStartTime = qStartTime;
    }

    public String getqEndTime() {
        return qEndTime;
    }

    public void setqEndTime(String qEndTime) {
        this.qEndTime = qEndTime;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
