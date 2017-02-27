/**
 * Project Name:jjd-background
 * File Name:City.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月16日上午11:02:26
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/**
 * ClassName: City <br/>
 * Date: 2015年12月16日 上午11:02:26 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_CITY")
public class City implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -8867232789167955014L;

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "PROVINCE_CODE")
    private String provinceCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SORT")
    private String sort;

    /**
     * 状态(1有效)
     */
    @Column(name = "STATE")
    private String state;

    private String provinceName;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Formula("(SELECT t.name FROM Province t WHERE t.code = provinceCode)")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
