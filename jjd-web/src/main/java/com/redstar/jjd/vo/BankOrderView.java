/**
 * Project Name:jjd-background
 * File Name:BankOrderView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年4月19日下午3:15:03
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: BankOrderView <br/>
 * Date: 2016年4月19日 下午3:15:03 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class BankOrderView {
    private Integer bankId;
    private String bankName;
    private Long storeId;
    private String storeName;

    /**
     * 是否有银行支付快捷通道（1：有，0：无）
     */
    private Boolean hasBankPay;

    private Double amount;

    private Long applyId;

    // 银行图标地址
    private String bankIcon;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Boolean getHasBankPay() {
        return hasBankPay;
    }

    public void setHasBankPay(Boolean hasBankPay) {
        this.hasBankPay = hasBankPay;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getBankIcon() {
        return bankIcon;
    }

    public void setBankIcon(String bankIcon) {
        this.bankIcon = bankIcon;
    }
}
