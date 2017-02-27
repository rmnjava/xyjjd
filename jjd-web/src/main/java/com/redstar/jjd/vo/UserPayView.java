/**
 * Project Name:jjd-weixin
 * File Name:UserPay.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年8月3日上午10:24:06
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.text.DecimalFormat;
import java.util.Date;

import com.redstar.jjd.utils.UtilDatetime;

/**
 * ClassName: UserPay <br/>
 * Date: 2016年8月3日 上午10:24:06 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserPayView {

    private Long id;

    private String userName;

    private String phone;

    private String provinceName;

    private String cityName;

    private String storeName;

    private String applyTime;

    /**
     * 申请日期
     */
    private Date applyDate;

    private String applyDateFmt;

    /**
     * 贷款额度（单位：元）
     */
    private Double amount;

    /**
     * 贷款期限（单位：月）
     */
    private Integer period;

    /**
     * 贷款利息
     */
    private Double interest;

    /**
     * 每月还款（单位：元）
     */
    private Double returnPerMonth;

    /**
     * 每月还款取整
     */
    private String returnPerMonthFmt;

    /**
     * 银行ID
     */
    private Long bankId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 交易金额
     */
    private String orderAmount;

    /**
     * 银行支付结果（成功 1，失败 0, 待支付2）
     */
    private String bankPayResult;

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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getApplyDateFmt() {
        if (applyDate != null) {
            return UtilDatetime.DATE_TIME_FULL_FORMAT.format(applyDate);
        }
        return "";
    }

    public String getReturnPerMonthFmt() {
        if (returnPerMonth > 0) {
            DecimalFormat df = new DecimalFormat("#");
            returnPerMonthFmt = df.format(returnPerMonth);
            return returnPerMonthFmt;
        }
        return "0";
    }

}
