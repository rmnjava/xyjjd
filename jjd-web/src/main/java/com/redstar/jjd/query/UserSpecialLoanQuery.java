package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import com.redstar.jjd.utils.UtilDatetime;

public class UserSpecialLoanQuery extends AbstractPagedQuery<Object[]> {

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
    private Long qbankId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 渠道
     */
    private String dataSource;

    /**
     * 商场id
     */
    private Long storeId;

    /**
     * 是否有意向贷款（1是 |0否）
     */
    private Boolean isLoan;

    /**
     * 是否已进件（1是 |0否）
     */
    private Boolean isInto;

    /**
     * 审批是否通过（1是 |0否）
     */
    private Boolean isLoanSuccess;

    /**
     * 顾客类型
     */
    private String qCustomerType;

    /**
     * 用户角色
     */
    private String roleName;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();
        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(distinct(a.ID)) ");
            sql.append("from T_USER_APPLY a left join T_USER_INFO u on u.ID=a.USER_INFO_ID ");
            sql.append("left join T_LOAN_INTENTION li on a.ID=li.APPLY_ID ");
            sql.append("left join T_LOAN_INTO it on a.ID=it.APPLY_ID ");
            sql.append("left join T_LOAN_EXAMINE le on a.ID=le.APPLY_ID ");
            sql.append("where a.ORDER_NO is null");
        } else {
            sql.append("select distinct(a.ID),a.APPLY_DATE,a.AMOUNT*10000 as sumamount,a.PERIOD,a.RETURN_PER_MONTH,a.USER_INFO_ID, ");
            sql.append("a.BANK_ID,a.STORE_ID,a.SOURCE_FROM,a.DATA_SOURCE,li.IS_LOAN,it.IS_INTO,le.IS_LOAN_SUCCESS,a.OPERATOR_ID,a.OPERATOR_TIME ");
            sql.append("from T_USER_APPLY a left join T_USER_INFO u on u.ID=a.USER_INFO_ID ");
            sql.append("left join T_LOAN_INTENTION li on a.ID=li.APPLY_ID ");
            sql.append("left join T_LOAN_INTO it on a.ID=it.APPLY_ID ");
            sql.append("left join T_LOAN_EXAMINE le on a.ID=le.APPLY_ID ");
            sql.append("where a.ORDER_NO is null ");
        }

        if (null != phone && !"".equals(phone)) {
            queryParams.put("phone", "%" + phone + "%");
            sql.append(" and u.USER_PHONE like :phone ");
        }

        if (null != qbankId && !new Long(-1L).equals(qbankId)) {
            queryParams.put("bankId", qbankId);
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
        if (null != dataSource && !("-1").equals(dataSource)) {
            queryParams.put("dataSource", dataSource);
            sql.append(" and a.DATA_SOURCE = :dataSource ");
        }

        if (null != isLoan && !"".equals(isLoan)) {
            queryParams.put("isLoan", isLoan);
            sql.append(" and li.IS_LOAN = :isLoan ");
        }

        if (null != isInto && !"".equals(isInto)) {
            queryParams.put("isInto", isInto);
            sql.append(" and it.IS_INTO = :isInto ");
        }

        if (null != isLoanSuccess && !"".equals(isLoanSuccess)) {
            queryParams.put("isLoanSuccess", isLoanSuccess);
            sql.append(" and le.IS_LOAN_SUCCESS = :isLoanSuccess ");
        }

        if (null != qCustomerType && !"-1".equals(qCustomerType)) {
            queryParams.put("qCustomerType", qCustomerType);
            sql.append(" and li.CUSTOMER_TYPE = :qCustomerType ");
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

    public Boolean getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(Boolean isLoan) {
        this.isLoan = isLoan;
    }

    public Boolean getIsInto() {
        return isInto;
    }

    public void setIsInto(Boolean isInto) {
        this.isInto = isInto;
    }

    public Boolean getIsLoanSuccess() {
        return isLoanSuccess;
    }

    public void setIsLoanSuccess(Boolean isLoanSuccess) {
        this.isLoanSuccess = isLoanSuccess;
    }

    public String getqCustomerType() {
        return qCustomerType;
    }

    public void setqCustomerType(String qCustomerType) {
        this.qCustomerType = qCustomerType;
    }

    public Long getQbankId() {
        return qbankId;
    }

    public void setQbankId(Long qbankId) {
        this.qbankId = qbankId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
