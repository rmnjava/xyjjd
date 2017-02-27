/**
 * Project Name:jjd-web
 * File Name:DepartmentQuery.java
 * Package Name:com.redstar.jjd.query
 * Date:2016年9月14日下午3:35:35
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.query;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.redstar.jjd.model.Dept;

/**
 * ClassName: DepartmentQuery <br/>
 * Date: 2016年9月14日 下午3:35:35 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class DeptQuery extends AbstractPagedQuery<Dept> {

    /**
     * 部门
     */
    private String deptName;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from Dept t where (1=1)");
        } else {
            sql.append("select t from Dept t where (1=1)");
        }

        if (StringUtils.isNotBlank(deptName)) {
            queryParams.put("deptName", "%" + deptName + "%");
            sql.append(" and (t.deptName like :deptName)");
        }

        return sql.toString();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
