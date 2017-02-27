/**
 * Project Name:jjd-background
 * File Name:Province.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月16日上午11:18:19
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClassName: Province <br/>
 * Date: 2015年12月16日 上午11:18:19 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_PROVINCE")
public class Province implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -5911332524091306285L;

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SORT")
    private String sort;

    /**
     * 状态(1有效)
     */
    @Column(name = "STATE")
    private String state;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
