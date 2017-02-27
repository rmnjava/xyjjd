/**
 * Project Name:jjd-web
 * File Name:Operator.java
 * Package Name:com.redstar.jjd.model
 * Date:2016年6月20日上午10:49:49
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * ClassName: Operator <br/>
 * Date: 2016年6月20日 上午10:49:49 <br/>
 * Description: 操作员table
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_OPERATOR")
public class Operator implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "operatorSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "operatorSequence", sequenceName = "SEQ_OPERATOR")
    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    // 账号
    @Column(name = "LOGINNAME")
    private String loginName;

    // 密码
    @Column(name = "PASSWORD")
    private String password;

    // 姓名
    @Column(name = "NAME")
    private String name;

    // 部门
    private String deptName;

    // 手机
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "STORE_ID")
    private Long storeId;

    // 多对多定义
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_SYS_U2R", joinColumns = { @JoinColumn(name = "OPERATOR_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    // Fecth策略定义
    @Fetch(FetchMode.SELECT)
    @OrderBy(value = "ROLE_ID")
    // 集合中对象id的缓存.
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Role> roleList = new ArrayList<Role>();

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * 获取系统角色名称,用","连接.
     * 
     * @return String 系统角色名称
     */
    @Transient
    public String getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        for (Role role : getRoleList()) {
            roleNames.add(role.getRoleName());
        }
        return StringUtils.join(roleNames, ",");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
