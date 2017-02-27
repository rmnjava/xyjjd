/**
 * Project Name:jjd-web
 * File Name:UserInfoView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年6月8日下午2:28:50
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * ClassName: UserInfoView <br/>
 * Date: 2016年6月8日 下午2:28:50 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserInfoView {

    private Long id;

    private String phone;

    private String storeName;

    private String idNumber;

    private String amount;

    private String orderNo;

    private String applyTime;

    private String orderTime;

    private String userName;

    private String companyType;

    private Integer income;

    private Boolean hasSocialSec;

    private Boolean hasAccumulation;

    private Boolean hasHouse;

    private Boolean hasCar;

    private Boolean hasCreditCard;

    private Date applyDate;

    private String bankName;

    /**
     * 每月还款（单位：元）
     */
    private Double returnPerMonth;

    /**
     * 每月还款取整
     */
    private String returnPerMonthFmt;

    /**
     * 贷款期限（单位：月）
     */
    private Integer period;

    /**
     * 用户来源
     */
    private String dataSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getReturnPerMonth() {
        return returnPerMonth;
    }

    public void setReturnPerMonth(Double returnPerMonth) {
        this.returnPerMonth = returnPerMonth;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getReturnPerMonthFmt() {
        if (returnPerMonth > 0) {
            DecimalFormat df = new DecimalFormat("#");
            returnPerMonthFmt = df.format(returnPerMonth);
            return returnPerMonthFmt;
        }
        return "0";
    }

    public void setReturnPerMonthFmt(String returnPerMonthFmt) {
        this.returnPerMonthFmt = returnPerMonthFmt;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
