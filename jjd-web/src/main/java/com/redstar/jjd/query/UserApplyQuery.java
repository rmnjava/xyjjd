package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.utils.UtilDatetime;

public class UserApplyQuery extends AbstractPagedQuery<UserApply> {

    /**
     * 开始时间
     */
    private String qStartTime;

    /**
     * 结束时间
     */
    private String qEndTime;

    private Long storeId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from UserApply t where (1=1) and t.orderNo is null and t.dataSource is null ");
        } else {
            sql.append("select t from UserApply t where (1=1) and t.orderNo is null and t.dataSource is null ");
        }
        if (null != qStartTime && !"".equals(qStartTime)) {
            queryParams.put("qStartTime", getStartTime());
            sql.append(" and (t.applyDate >= :qStartTime)");
        }
        if (null != qEndTime && !"".equals(qEndTime)) {
            queryParams.put("qEndTime", getEndTime());
            sql.append(" and (t.applyDate <= :qEndTime)");
        }
        if (null != storeId) {
            queryParams.put("storeId", storeId);
            sql.append(" and t.storeId = :storeId ");
        }
        sql.append(" order by t.applyDate desc");
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
