/**
 * Project Name:jjd-web
 * File Name:DeptView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年9月14日下午4:17:36
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: DeptView <br/>
 * Date: 2016年9月14日 下午4:17:36 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class DeptView {

    private Long deptId;

    /**
     * 部门
     */
    private String deptName;

    private String deptDesc;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

}
