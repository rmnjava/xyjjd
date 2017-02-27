/**
 * Project Name:jjd-web
 * File Name:LoanIntentionView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年11月29日下午3:19:31
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.util.Date;

/**
 * ClassName: LoanIntentionView <br/>
 * Date: 2016年11月29日 下午3:19:31 <br/>
 * Description: 贷款进件view
 * 
 * @author huangrui
 * @version
 * @see
 */
public class LoanIntoView {
    private Long id;

    /**
     * 申请id
     */
    private Long applyId;

    /**
     * 是否已进件（1是 |0否）
     */
    private Boolean isInto;

    /**
     * 进件日期
     */
    private String intoTime;
    private Date intoDate;

    /**
     * 操作时间
     */
    private String operatorTime;

    /**
     * 操作人
     */
    private Long operatorId;

    /**
     * 备注
     */
    private String note;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作人部门
     */
    private String deptName;

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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Boolean getIsInto() {
        return isInto;
    }

    public void setIsInto(Boolean isInto) {
        this.isInto = isInto;
    }

    public String getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(String intoTime) {
        this.intoTime = intoTime;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Date getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(Date intoDate) {
        this.intoDate = intoDate;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
