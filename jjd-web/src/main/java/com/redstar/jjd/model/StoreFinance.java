/**
 * Project Name:jjd-web
 * File Name:StoreFinance.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年7月8日下午4:11:57
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * ClassName: StoreFinance <br/>
 * Date: 2016年7月8日 下午4:11:57 <br/>
 * Description: 商场财务
 * 
 * @author huangrui
 * @version
 * @see
 */
// @Entity
// @Table(name = "T_STORE_FINANCE")
public class StoreFinance implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 3614377524235500849L;
    @Id
    @SequenceGenerator(name = "storeFinanceSequence", sequenceName = "SEQ_STOREFINANCE")
    @GeneratedValue(generator = "storeFinanceSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 申请id
     */
    @Column(name = "APPLY_ID")
    private Long applyId;

    /**
     * 建单确认(1确认|0未确认)
     */
    @Column(name = "CONFIRM_CREATE_ORDER")
    private Boolean confirmCreateOrder;

    /**
     * 建单金额
     */
    @Column(name = "CREATE_ORDER_AMOUNT")
    private String createOrderAmount;

    /**
     * 通知领卡（1是 0否）
     */
    @Column(name = "IS_NOTICE_CARD")
    private Boolean isNoticeCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Boolean getConfirmCreateOrder() {
        return confirmCreateOrder;
    }

    public void setConfirmCreateOrder(Boolean confirmCreateOrder) {
        this.confirmCreateOrder = confirmCreateOrder;
    }

    public Boolean getIsNoticeCard() {
        return isNoticeCard;
    }

    public void setIsNoticeCard(Boolean isNoticeCard) {
        this.isNoticeCard = isNoticeCard;
    }

}
