/**
 * Project Name:jjd-web
 * File Name:Customer400.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年7月8日下午3:44:47
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
 * ClassName: LoanIntention <br/>
 * Date: 2016年11月28日 下午3:35:30 <br/>
 * Description: 贷款意向 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
@Entity
@Table(name = "T_LOAN_INTENTION")
public class LoanIntention implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -7266165956133095829L;

    @Id
    @SequenceGenerator(name = "loanIntentionSequence", sequenceName = "SEQ_LOANINTENTION")
    @GeneratedValue(generator = "loanIntentionSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 申请id
     */
    @Column(name = "APPLY_ID")
    private Long applyId;

    /**
     * 是否有意向贷款（1是 |0否）
     */
    @Column(name = "IS_LOAN")
    private Boolean isLoan;

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

    /**
     * 客户类型（A类，B类，C类，D其他）
     */
    @Column(name = "CUSTOMER_TYPE")
    private String customerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(Boolean isLoan) {
        this.isLoan = isLoan;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
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

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

}
