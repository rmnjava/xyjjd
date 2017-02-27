package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.utils.UtilDatetime;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class UserQuery extends AbstractPagedQuery<Object[]> {

    /**
     * 开始时间
     */
    private String qStartTime;

    /**
     * 结束时间
     */
    private String qEndTime;

    /**
     * 数据来源
     */
    private String dataSource;

    private Long storeId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER u,T_STORE s,T_BANK b where t.USER_APPLY_ID=a.ID ");
            sql.append(" and t.USER_ID=u.ID and a.STORE_ID=s.ID and a.BANK_ID=b.ID and a.DATA_SOURCE is null ");
        } else {
            sql.append("select t.USER_ID,a.APPLY_DATE,t.ORDER_NO,u.PHONE,a.AMOUNT*10000 as sumamount,s.NAME as storeName,");
            sql.append("a.PERIOD,a.RETURN_PER_MONTH,a.DATA_SOURCE,t.HOST_DATE,b.NAME as bankName,a.ID as applyId ");
            sql.append(" from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER u,T_STORE s ,T_BANK b where t.USER_APPLY_ID=a.ID ");
            sql.append(" and t.USER_ID=u.ID and a.STORE_ID=s.ID and a.BANK_ID=b.ID and a.DATA_SOURCE is null ");
        }

        if (null != qStartTime && !"".equals(qStartTime)) {
            queryParams.put("qStartTime", getStartTime());
            sql.append(" and (t.ORDER_DATE >= :qStartTime) ");
        }
        if (null != qEndTime && !"".equals(qEndTime)) {
            queryParams.put("qEndTime", getEndTime());
            sql.append(" and (t.ORDER_DATE <= :qEndTime)");
        }
        if (null != storeId) {
            queryParams.put("storeId", storeId);
            sql.append(" and a.STORE_ID = :storeId ");
        }
        sql.append(" and t.BANK_PAYRESULT=1 order by t.ORDER_DATE desc");
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

    public String getqStartTime() {
        return qStartTime;
    }

    public void setqStartTime(String qStartTime) {
        this.qStartTime = qStartTime;
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

    public String getqEndTime() {
        return qEndTime;
    }

    public void setqEndTime(String qEndTime) {
        this.qEndTime = qEndTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
