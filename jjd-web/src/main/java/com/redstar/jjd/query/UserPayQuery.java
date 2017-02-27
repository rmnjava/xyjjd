/**
 * Project Name:jjd-web
 * File Name:UserPayQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年8月23日下午5:17:02
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.model.UserPay;
import com.redstar.jjd.utils.UtilDatetime;

/**
 * ClassName: UserPayQuery <br/>
 * Date: 2016年8月23日 下午5:17:02 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserPayQuery extends AbstractPagedQuery<UserPay> {
    /**
     * 开始时间
     */
    private String qStartTime;

    /**
     * 结束时间
     */
    private String qEndTime;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from UserPay t where 1=1 ");
        } else {
            sql.append("select t from UserPay t where 1=1 ");
        }
        if (null != qStartTime && !"".equals(qStartTime)) {
            queryParams.put("qStartTime", getStartTime());
            sql.append(" and (t.applyDate >= :qStartTime)");
        }
        if (null != qEndTime && !"".equals(qEndTime)) {
            queryParams.put("qEndTime", getEndTime());
            sql.append(" and (t.applyDate <= :qEndTime)");
        }
        sql.append("and t.bankPayResult=1 order by t.applyDate desc");
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
}
