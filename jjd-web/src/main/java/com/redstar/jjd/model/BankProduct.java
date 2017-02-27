package com.redstar.jjd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Pengfei on 2015/12/18.
 */
@Entity
@Table(name = "T_BANK_PRODUCT")
public class BankProduct implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -958339326887726180L;
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
    private Boolean state;

    /**
     * 是否有银行支付快捷通道（1：有，0：无）
     */

    private Boolean hasBankPay;

    /**
     * 合作开始时间
     */

    private Date startTime;

    /**
     * 合作结束时间
     */

    private Date endTime;

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

    @Id
    @SequenceGenerator(name = "bankProductSequence", sequenceName = "SEQ_BANK_PRODUCT")
    @GeneratedValue(generator = "bankProductSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "BANK_ID")
    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Column(name = "TYPE_ID")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Column(name = "MAX_AMOUNT")
    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Column(name = "MIN_AMOUNT")
    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    @Column(name = "MAX_PERIOD")
    public Integer getMaxPeroid() {
        return maxPeriod;
    }

    public void setMaxPeroid(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Column(name = "STATE")
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Column(name = "HAS_BANKPAY")
    public Boolean getHasBankPay() {
        return hasBankPay;
    }

    public void setHasBankPay(Boolean hasBankPay) {
        this.hasBankPay = hasBankPay;
    }

    @Column(name = "REQUIRE_MATERIAL")
    public String getRequireMaterial() {
        return requireMaterial;
    }

    public void setRequireMaterial(String requireMaterial) {
        this.requireMaterial = requireMaterial;
    }

    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    @Column(name = "APPLY_CONDITION")
    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    @Column(name = "INCOMINGSTANDARD")
    public String getIncomingStandard() {
        return incomingStandard;
    }

    public void setIncomingStandard(String incomingStandard) {
        this.incomingStandard = incomingStandard;
    }

    @Column(name = "CREDITPOLICY")
    public String getCreditPolicy() {
        return creditPolicy;
    }

    public void setCreditPolicy(String creditPolicy) {
        this.creditPolicy = creditPolicy;
    }

    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "START_TIME")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "END_TIME")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "PAYMETHOD_DESC")
    public String getPayMethodDesc() {
        return payMethodDesc;
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

}
