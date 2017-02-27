/**
 * Project Name:jjd-web
 * File Name:ApplyResult.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年7月8日下午3:55:45
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
 * 
 * ClassName: LoanInto <br/>
 * Date: 2016年11月28日 下午3:38:57 <br/>
 * Description: 贷款进件 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
@Entity
@Table(name = "T_LOAN_INTO")
public class LoanInto implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -2278404503515691215L;

    @Id
    @SequenceGenerator(name = "loanIntoSequence", sequenceName = "SEQ_LOANINTO")
    @GeneratedValue(generator = "loanIntoSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 申请id
     */
    @Column(name = "APPLY_ID")
    private Long applyId;

    /**
     * 是否已进件（1是 |0否）
     */
    @Column(name = "IS_INTO")
    private Boolean isInto;

    /**
     * 进件日期
     */
    @Column(name = "INTO_DATE")
    private Date intoDate;

    /**
     * 操作时间
     */
    @Column(name = "OPERATOR_TIME")
    private Date operatorTime;

    /**
     * 操作人
     */
    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    /**
     * 备注
     */
    @Column(name = "NOTE")
    private String note;

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

    public Date getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(Date intoDate) {
        this.intoDate = intoDate;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsInto() {
        return isInto;
    }

    public void setIsInto(Boolean isInto) {
        this.isInto = isInto;
    }

}
