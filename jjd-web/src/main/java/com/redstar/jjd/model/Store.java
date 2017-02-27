/**
 * Project Name:jjd-background
 * File Name:Store.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月22日上午10:10:57
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ClassName: Store <br/>
 * Date: 2015年12月22日 上午10:10:57 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_STORE")
public class Store implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -6756505446129933659L;
    private Long id;
    private String cityCode;
    private String name;
    private String address;

    // // 大区（京沪西南|华北东北|华东|华中华南）
    // private String area;
    // // 小区
    // private String sarea;
    // // 通联系统机构号
    // private String communicationSysNo;
    // // 通联系统机构名称
    // private String communicationSysName;
    // // 新系统机构号
    // private String newSysNo;
    // // 新系统机构名称
    // private String newSysName;
    // // 合作金融机构
    // private Long bankBranchId;
    // // 金融机构对接人姓名
    // private String bankBrokerName;
    // // 金融机构对接人手机号
    // private String bankBrokerPhone;

    @Id
    @SequenceGenerator(name = "storeSequence", sequenceName = "SEQ_STORE")
    @GeneratedValue(generator = "storeSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CITY_CODE")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
