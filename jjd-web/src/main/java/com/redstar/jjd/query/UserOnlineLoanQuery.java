package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.utils.UtilDatetime;

public class UserOnlineLoanQuery extends AbstractPagedQuery<Object[]> {

    /**
     * 开始时间
     */
    private String qStartTime;

    /**
     * 结束时间
     */
    private String qEndTime;

    /**
     * 银行
     */
    private Long bankId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 贷款是否成功
     */
    private String bankPayResult;

    /**
     * 渠道
     */
    private String dataSource;

    private Long storeId;

    /**
     * 用户角色
     */
    private String roleName;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER_INFO u where t.USER_APPLY_ID=a.ID ");
            sql.append("and u.ID=a.USER_INFO_ID ");
        } else {
            sql.append("select a.ID,a.APPLY_DATE,t.ORDER_NO,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH, ");
            sql.append("a.BANK_PAYRESULT,a.USER_INFO_ID,a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,t.HOST_DATE,a.DATA_SOURCE ");
            sql.append(" from T_ORDER_BANK_PAY t,T_USER_APPLY a,T_USER_INFO u where t.USER_APPLY_ID=a.ID ");
            sql.append(" and u.ID=a.USER_INFO_ID ");
        }

        if (null != orderNo && !"".equals(orderNo)) {
            queryParams.put("orderNo", "%" + orderNo + "%");
            sql.append(" and a.ORDER_NO like :orderNo ");
        }

        if (null != phone && !"".equals(phone)) {
            queryParams.put("phone", "%" + phone + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != bankId && !new Long(-1L).equals(bankId)) {
            queryParams.put("bankId", bankId);
            sql.append(" and a.BANK_ID = :bankId ");
        }

        if (null != bankPayResult && !"".equals(bankPayResult)) {
            queryParams.put("bankPayResult", bankPayResult);
            sql.append(" and a.BANK_PAYRESULT =:bankPayResult ");
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
        if (null != dataSource && !("-1").equals(dataSource)) {
            queryParams.put("dataSource", dataSource);
            sql.append(" and a.DATA_SOURCE = :dataSource ");
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBankPayResult() {
        return bankPayResult;
    }

    public void setBankPayResult(String bankPayResult) {
        this.bankPayResult = bankPayResult;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
