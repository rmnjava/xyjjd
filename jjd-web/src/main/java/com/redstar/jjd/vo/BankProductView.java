package com.redstar.jjd.vo;

import java.util.Date;

public class BankProductView {
    private Long id;
    private Long bankId;
    private String bankName;
    /**
     * 1.固定利率还款 2.不定利率分期还款
     */
    private Integer typeId;
    /**
     * 最大贷款额度（单位：万）
     */
    private Double maxAmount;
    /**
     * 最小贷款额度（单位：万）
     */
    private Double minAmount;
    /**
     * 最长贷款期限（单位：月）
     */
    private Integer maxPeriod;
    /**
     * 产品介绍
     */
    private String Description;

    /**
     * 申请条件
     */
    private String applyCondition;
    /**
     * 所需材料
     */
    private String requireMaterial;
    /**
     * 状态（1：有效）
     */
    private Boolean state = true;

    /**
     * 是否有银行支付快捷通道（1：有，0：无）
     */
    private Boolean hasBankPay;

    /**
     * 合作开始时间
     */
    private Date startTime;

    private String start;

    /**
     * 合作结束时间
     */
    private Date endTime;

    private String end;

    /**
     * 进件资料标准
     */
    private String incomingStandard;

    /**
     * 还款方式
     */
    private String payMethodDesc;

    /**
     * 授信政策
     */
    private String creditPolicy;

    /**
     * 备注信息
     */
    private String note;

    private BankProductReturnMethodView bankProductReturnMethodView;

    public BankProductReturnMethodView getBankProductReturnMethodView() {
        return bankProductReturnMethodView;
    }

    public void setBankProductReturnMethodView(
            BankProductReturnMethodView bankProductReturnMethodView) {
        this.bankProductReturnMethodView = bankProductReturnMethodView;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public String getRequireMaterial() {
        return requireMaterial;
    }

    public void setRequireMaterial(String requireMaterial) {
        this.requireMaterial = requireMaterial;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getHasBankPay() {
        return hasBankPay;
    }

    public void setHasBankPay(Boolean hasBankPay) {
        this.hasBankPay = hasBankPay;
    }

    public String getIncomingStandard() {
        return incomingStandard;
    }

    public void setIncomingStandard(String incomingStandard) {
        this.incomingStandard = incomingStandard;
    }

    public String getCreditPolicy() {
        return creditPolicy;
    }

    public void setCreditPolicy(String creditPolicy) {
        this.creditPolicy = creditPolicy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

}
