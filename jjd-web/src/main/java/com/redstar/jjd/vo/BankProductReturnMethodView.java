/**
 * Project Name:jjd-web
 * File Name:BankProductReturnMethodView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年9月28日下午2:21:15
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.text.DecimalFormat;

/**
 * ClassName: BankProductReturnMethodView <br/>
 * Date: 2016年9月28日 下午2:21:15 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class BankProductReturnMethodView {
    private Long rateId;
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
    private Double companyAllowance;

    /**
     * 商场补贴
     */
    private Double storeAllowance;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getInterestFmt() {
        DecimalFormat df = new DecimalFormat("0.00%");
        if (interest != null) {
            return df.format(interest);
        }
        return "0.0";
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Boolean getIsZero() {
        return isZero;
    }

    public void setIsZero(Boolean isZero) {
        this.isZero = isZero;
    }

    public Integer getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(Integer returnMonth) {
        this.returnMonth = returnMonth;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getCompanyAllowanceFmt() {
        DecimalFormat df = new DecimalFormat("0.00%");
        if (companyAllowance != null) {
            return df.format(companyAllowance);
        }
        return "0.0";
    }

    public Double getCompanyAllowance() {
        return companyAllowance;
    }

    public void setCompanyAllowance(Double companyAllowance) {
        this.companyAllowance = companyAllowance;
    }

    public String getStoreAllowanceFmt() {
        DecimalFormat df = new DecimalFormat("0.00%");
        if (storeAllowance != null) {
            return df.format(storeAllowance);
        }
        return "0.0";
    }

    public Double getStoreAllowance() {
        return storeAllowance;
    }

    public void setStoreAllowance(Double storeAllowance) {
        this.storeAllowance = storeAllowance;
    }

    public static void main(String args[]) {
        BankProductReturnMethodView view = new BankProductReturnMethodView();
        System.out.println(view.getCompanyAllowanceFmt());
    }
}
