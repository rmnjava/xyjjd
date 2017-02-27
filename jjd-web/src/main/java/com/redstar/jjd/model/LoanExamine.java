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
 * ClassName: LoanExamine <br/>
 * Date: 2016年11月28日 下午3:40:40 <br/>
 * Description: 贷款审批 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
@Entity
@Table(name = "T_LOAN_EXAMINE")
public class LoanExamine implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 849564982313423623L;
    @Id
    @SequenceGenerator(name = "loanExamineSequence", sequenceName = "SEQ_LOANEXAMINE")
    @GeneratedValue(generator = "loanExamineSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 申请id
     */
    @Column(name = "APPLY_ID")
    private Long applyId;

    /**
     * 是否放款已成功（1是 |0否）
     */
    @Column(name = "IS_LOAN_SUCCESS")
    private Boolean isLoanSuccess;

    /**
     * 到账日期
     */
    @Column(name = "ARRIVAL_DATE")
    private Date arrivalDate;

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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public Boolean getIsLoanSuccess() {
        return isLoanSuccess;
    }

    public void setIsLoanSuccess(Boolean isLoanSuccess) {
        this.isLoanSuccess = isLoanSuccess;
    }

}
