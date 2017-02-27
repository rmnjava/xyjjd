/**
 * Project Name:jjd-background
 * File Name:User.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月15日下午6:31:48
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ClassName: User <br/>
 * Date: 2015年12月15日 下午6:31:48 <br/>
 * Description: user实体类
 * 
 * @author huangrui
 * @version
 * @see
 */

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -1277725568664007287L;
    @Id
    @GeneratedValue(generator = "userSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "userSequence", sequenceName = "SEQ_USER")
    @Column(name = "ID")
    private Long id;
    /**
     * 通汇卡userId 此id用于跟通汇卡交互
     */
    @Column(name = "USER_CARDID")
    private String userCardId;

    /**
     * 手机号用于app登录
     */
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "OPEN_ID")
    private String openId;

    /**
     * 报名渠道（1 app(phone,password)，2 wechat(openId)，3 h5 4 pc 5 商场补录）
     */
    @Column(name = "ENTER_CHANNEL")
    private String enterChannel;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getEnterChannel() {
        return enterChannel;
    }

    public void setEnterChannel(String enterChannel) {
        this.enterChannel = enterChannel;
    }

}
