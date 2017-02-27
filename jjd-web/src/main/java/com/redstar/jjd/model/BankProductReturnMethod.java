package com.redstar.jjd.model;

import java.io.Serializable;

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
@Table(name = "T_BANK_PRODUCT_RETURN_METHOD")
public class BankProductReturnMethod implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -4363812030923240690L;
    private Long id;
    private Long productId;
    /**
     * 还款期限（单位：月）
     */
    private Integer period;
    /**
     * 利率
     */
    private Double interest;
    /**
     * 是否是0利率（1：是）
     */
    private Boolean isZero;
    /**
     * 分期还款的话是每几个月还一次
     */
    private Integer returnMonth;

    /**
     * 星易通汇补贴
     */
    @Column(name = "COMPANY_ALLOWANCE")
    private Double companyAllowance;

    /**
     * 商场补贴
     */
    @Column(name = "STORE_ALLOWANCE")
    private Double storeAllowance;

    @Id
    @SequenceGenerator(name = "bankProductReturnMethodSequence", sequenceName = "SEQ_BANK_PRODUCT_RETURN_METHOD")
    @GeneratedValue(generator = "bankProductReturnMethodSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "PRODUCT_ID")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "PERIOD")
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Column(name = "INTEREST")
    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    @Column(name = "IS_ZERO")
    public Boolean getIsZero() {
        return isZero;
    }

    public void setIsZero(Boolean isZero) {
        this.isZero = isZero;
    }

    @Column(name = "RETURN_MONTH")
    public Integer getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(Integer returnMonth) {
        this.returnMonth = returnMonth;
    }

    public Double getCompanyAllowance() {
        return companyAllowance;
    }

    public void setCompanyAllowance(Double companyAllowance) {
        this.companyAllowance = companyAllowance;
    }

    public Double getStoreAllowance() {
        return storeAllowance;
    }

    public void setStoreAllowance(Double storeAllowance) {
        this.storeAllowance = storeAllowance;
    }

}
