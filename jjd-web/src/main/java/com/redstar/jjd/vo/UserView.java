/**
 * Project Name:jjd-background
 * File Name:UserVo.java
 * Package Name:com.redstar.jjd.view
 * Date:2015年12月18日上午9:55:35
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: UserVo <br/>
 * Date: 2015年12月18日 上午9:55:35 <br/>
 * Description: 用户信息vo类
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserView {

    private static final long serialVersionUID = 4773001990318463258L;

    private Long id;

    private String userName;

    private String phone;

    private String password;

    private String authCode;

    private String userCardId;

    /**
     * 交易码密文
     */
    private String payCode;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 新密码
     */
    private String newPassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
