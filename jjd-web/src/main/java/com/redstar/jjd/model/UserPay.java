/**
 * Project Name:jjd-weixin
 * File Name:UserPay.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年8月3日上午10:24:06
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

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
 * ClassName: UserPay <br/>
 * Date: 2016年8月3日 上午10:24:06 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_USER_PAY")
public class UserPay implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -3052544332247339151L;

    @Id
    @SequenceGenerator(name = "userPaySequence", sequenceName = "SEQ_USER_PAY")
    @GeneratedValue(generator = "userPaySequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PROVINCE_NAME")
    private String provinceName;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "STORE_NAME")
    private String storeName;

    /**
     * 申请日期
     */
    @Column(name = "APPLY_DATE")
    private Date applyDate;

    /**
     * 贷款额度（单位：元）
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
     * 银行名称
     */
    @Column(name = "BANK_NAME")
    private String bankName;

    /**
     * 订单日期 YYYY/MM/DD HH:MM:SS
     */
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    /**
     * 订单编号
     */
    @Column(name = "ORDER_NO")
    private String orderNo;

    /**
     * 交易金额
     */
    @Column(name = "ORDER_AMOUNT")
    private String orderAmount;

    /**
     * 银行支付结果（成功 1，失败 0, 待支付2）
     */
    @Column(name = "BANK_PAYRESULT")
    private String bankPayResult;

    /**
     * 交易凭证号（建议使用 iRspRef 作为对账依据）
     */
    @Column(name = "VOUCHER_NO")
    private String voucherNo;

    /**
     * 交易批次号
     */
    @Column(name = "BATCH_NO")
    private String batchNo;

    /**
     * 银行交易日期 YYYY/MM/DD HH:MM:SS
     */
    @Column(name = "HOST_DATE")
    private Date hostDate;

    /**
     * 银行返回交易流水号
     */
    @Column(name = "IRSP_REF")
    private String iRspRef;

    /**
     * 消费者支付方式
     */
    @Column(name = "PAY_TYPE")
    private String payType;

    /**
     * 支付结果通知方式
     */
    @Column(name = "NOTIFY_TYPE")
    private String notifyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBankPayResult() {
        return bankPayResult;
    }

    public void setBankPayResult(String bankPayResult) {
        this.bankPayResult = bankPayResult;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getHostDate() {
        return hostDate;
    }

    public void setHostDate(Date hostDate) {
        this.hostDate = hostDate;
    }

    public String getiRspRef() {
        return iRspRef;
    }

    public void setiRspRef(String iRspRef) {
        this.iRspRef = iRspRef;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
