/**
 * Project Name:jjd-background
 * File Name:User.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月15日下午6:31:48
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * ClassName: UserInfo <br/>
 * Date: 2015年12月16日 上午10:31:59 <br/>
 * Description: userinfo实体类 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
@Entity
@Table(name = "T_USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(generator = "userInfoSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "userInfoSequence", sequenceName = "SEQ_USER_INFO")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ID_NUMBER")
    private String idNumber;

    @Column(name = "COMPANY_TYPE")
    private String companyType;

    @Column(name = "INCOME")
    private Integer income;

    @Column(name = "HAS_SOCIAL_SEC")
    private Boolean hasSocialSec;

    @Column(name = "HAS_ACCUMULATION")
    private Boolean hasAccumulation;

    @Column(name = "HAS_HOUSE")
    private Boolean hasHouse;

    @Column(name = "HAS_CAR")
    private Boolean hasCar;

    @Column(name = "HAS_CREDIT_CARD")
    private Boolean hasCreditCard;

    /**
     * 用于微信报名手机号，一个openId可以报名多个手机号
     */
    @Column(name = "USER_PHONE")
    private String userPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
