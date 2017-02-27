/**
 * Project Name:jjd-web
 * File Name:OperatorView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年7月4日下午2:17:00
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: OperatorView <br/>
 * Date: 2016年7月4日 下午2:17:00 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class OperatorView {

    private Long operatorId;

    private String loginName;

    private String password;

    private String userMD5Pwd;

    private Integer status;

    /**
     * 姓名
     */
    private String name;

    private String deptName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 部门
     */
    private Long deptId;

    /**
     * 角色列表
     */
    private List<RoleView> roleList;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    private Long storeId;

    private String provinceCode;

    private String cityCode;

    private Timestamp createTime;
    private String createTimeStr;

    /**
     * 获取系统角色名称,用","连接.
     * 
     * @return String 系统角色名称
     */
    @Transient
    public String getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        for (RoleView role : getRoleList()) {
            roleNames.add(role.getRoleName());
        }
        return StringUtils.join(roleNames, ",");
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<RoleView> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleView> roleList) {
        this.roleList = roleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getUserMD5Pwd() {
        return userMD5Pwd;
    }

    public void setUserMD5Pwd(String userMD5Pwd) {
        this.userMD5Pwd = userMD5Pwd;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

}
