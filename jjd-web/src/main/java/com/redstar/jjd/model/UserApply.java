package com.redstar.jjd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * ClassName: UserApply <br/>
 * Date: 2016年8月10日 下午5:58:34 <br/>
 * Description: 贷款申请信息 <br/>
 * 
 * @author lenovo
 * @version
 * @see
 */
@Entity
@Table(name = "T_USER_APPLY")
public class UserApply {
    @Id
    @GeneratedValue(generator = "userApplySequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "userApplySequence", sequenceName = "SEQ_USER_APPLY")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "OPEN_ID")
    private String openId;

    @Column(name = "USER_INFO_ID")
    private Long userInfoId;

    @Column(name = "COMPANY_TYPE")
    private String companyType;

    /**
     * 月薪
     */
    @Column(name = "INCOME")
    private Integer income;

    /**
     * 是否有本地社保
     */
    @Column(name = "HAS_SOCIAL_SEC")
    private Boolean hasSocialSec;

    /**
     * 是否有公积金
     */
    @Column(name = "HAS_ACCUMULATION")
    private Boolean hasAccumulation;

    /**
     * 是否有房产
     */
    @Column(name = "HAS_HOUSE")
    private Boolean hasHouse;

    /**
     * 是否有车
     */
    @Column(name = "HAS_CAR")
    private Boolean hasCar;

    /**
     * 是否有信用卡
     */
    @Column(name = "HAS_CREDIT_CARD")
    private Boolean hasCreditCard;

    /**
     * 申请日期
     */
    @Column(name = "APPLY_DATE")
    private Date applyDate;

    /**
     * 申请状态（0：审批中，1：成功，2：失败）
     */
    @Column(name = "APPLY_STATUS")
    private Integer applyStatus;

    /**
     * 贷款额度（单位：万元）
     */
    @Column(name = "AMOUNT")
    private Double amount;

    /**
     * 贷款期限（单位：月）
     */
    @Column(name = "PERIOD")
    private Integer period;

    /**
     * 贷款利息
     */
    @Column(name = "INTEREST")
    private Double interest;

    /**
     * 每月还款（单位：元）
     */
    @Column(name = "RETURN_PER_MONTH")
    private Double returnPerMonth;

    /**
     * 银行ID
     */
    @Column(name = "BANK_ID")
    private Long bankId;

    /**
     * 1.固定利率还款 2.不定利率分期还款
     */
    @Column(name = "RETURN_TYPE")
    private Integer returnType;

    /**
     * 分期还款的话是每几个月还一次
     */
    @Column(name = "RETURN_MONTH")
    private Integer returnMonth;

    @Column(name = "STORE_ID")
    private Long storeId;

    @Column(name = "IS_ZERO")
    private Boolean isZero;

    /**
     * 订单编号
     */
    @Column(name = "ORDER_NO")
    private String orderNo;

    /**
     * 银行支付结果（成功 1，失败 0, 待支付2）
     */
    @Column(name = "BANK_PAYRESULT")
    private String bankPayResult;

    /**
     * 此字段用来区分是来源于农行还是红星（农行0 红星1）
     */
    @Column(name = "SOURCE_FROM")
    private String sourceFrom;

    /**
     * 数据来源（1 app目前是“”，2 wechat,3 H5,4 PC, 5 商场录入）
     */
    @Column(name = "DATA_SOURCE")
    private String dataSource;

    /**
     * 是否有意向贷款（1是 |0否）
     */
    @Column(name = "IS_LOAN")
    private Boolean isLoan;

    /**
     * 是否已进件（1是 |0否）
     */
    @Column(name = "IS_INTO")
    private Boolean isInto;

    /**
     * 是否放款已成功（1是 |0否）
     */
    @Column(name = "IS_LOAN_SUCCESS")
    private Boolean isLoanSuccess;

    /**
     * 录入时间
     */
    @Column(name = "OPERATOR_TIME")
    private Date operatorTime;

    /**
     * 录入人
     */
    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    /**
     * 备注
     */
    @Column(name = "LOAN_NOTE")
    private String loanNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Boolean getHasSocialSec() {
        return hasSocialSec;
    }

    public void setHasSocialSec(Boolean hasSocialSec) {
        this.hasSocialSec = hasSocialSec;
    }

    public Boolean getHasAccumulation() {
        return hasAccumulation;
    }

    public void setHasAccumulation(Boolean hasAccumulation) {
        this.hasAccumulation = hasAccumulation;
    }

    public Boolean getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(Boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public Boolean getHasCar() {
        return hasCar;
    }

    public void setHasCar(Boolean hasCar) {
        this.hasCar = hasCar;
    }

    public Boolean getHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(Boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getReturnPerMonth() {
        return returnPerMonth;
    }

    public void setReturnPerMonth(Double returnPerMonth) {
        this.returnPerMonth = returnPerMonth;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(Integer returnMonth) {
        this.returnMonth = returnMonth;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Boolean getIsZero() {
        return isZero;
    }

    public void setIsZero(Boolean isZero) {
        this.isZero = isZero;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getBankPayResult() {
        return bankPayResult;
    }

    public void setBankPayResult(String bankPayResult) {
        this.bankPayResult = bankPayResult;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
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

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getLoanNote() {
        return loanNote;
    }

    public void setLoanNote(String loanNote) {
        this.loanNote = loanNote;
    }

}
