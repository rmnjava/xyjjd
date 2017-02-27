/**
 * Project Name:jjd-web
 * File Name:GroupFinance.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年7月8日下午4:11:33
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * ClassName: GroupFinance <br/>
 * Date: 2016年7月8日 下午4:11:33 <br/>
 * Description: 集团财务
 * 
 * @author huangrui
 * @version
 * @see
 */
// @Entity
// @Table(name = "T_GROUP_FINANCE")
public class GroupFinance implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 849564982313423623L;
    @Id
    @SequenceGenerator(name = "groupFinancSequence", sequenceName = "SEQ_GROUPFINANC")
    @GeneratedValue(generator = "groupFinancSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 确认到账(已到账)
     */
    @Column(name = "CONFIRM_ARRIVAL")
    private Boolean confirmArrival;

    /**
     * 确认到账时间
     */
    @Column(name = "CONFIRM_ARRIVAL_TIME")
    private Timestamp confirmArrivalTime;

    /**
     * 通汇卡订单号
     */
    @Column(name = "CARD_ORDERNO")
    private String cardOrderNo;

    /**
     * 确认充值(1 已充值)
     */
    @Column(name = "CONFIRM_DEPOSIT")
    private Boolean confirmDeposit;
    /**
     * 确认充值时间
     */
    @Column(name = "CONFIRM_DEPOSIT_TIME")
    private Timestamp confirmDepositTime;

    /**
     * 申请id
     */
    @Column(name = "APPLY_ID")
    private Long applyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getConfirmArrival() {
        return confirmArrival;
    }

    public void setConfirmArrival(Boolean confirmArrival) {
        this.confirmArrival = confirmArrival;
    }

    public Timestamp getConfirmArrivalTime() {
        return confirmArrivalTime;
    }

    public void setConfirmArrivalTime(Timestamp confirmArrivalTime) {
        this.confirmArrivalTime = confirmArrivalTime;
    }

    public String getCardOrderNo() {
        return cardOrderNo;
    }

    public void setCardOrderNo(String cardOrderNo) {
        this.cardOrderNo = cardOrderNo;
    }

    public Boolean getConfirmDeposit() {
        return confirmDeposit;
    }

    public void setConfirmDeposit(Boolean confirmDeposit) {
        this.confirmDeposit = confirmDeposit;
    }

    public Timestamp getConfirmDepositTime() {
        return confirmDepositTime;
    }

    public void setConfirmDepositTime(Timestamp confirmDepositTime) {
        this.confirmDepositTime = confirmDepositTime;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
}
