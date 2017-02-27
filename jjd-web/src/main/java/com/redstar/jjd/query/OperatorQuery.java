package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.redstar.jjd.model.Operator;
import com.redstar.jjd.utils.UtilDatetime;

/**
 * 
 * ClassName: RoleQuery <br/>
 * Date: 2016年6月20日 下午5:53:44 <br/>
 * Description: TODO <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
public class OperatorQuery extends AbstractPagedQuery<Operator> {

    private Long operatorId;

    private String qloginName;

    private String startTime;
    private String endTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String deptName;

    /**
     * 角色Id
     * */
    private Long qroleId;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from Operator t join t.roleList r where (1=1)");
        } else {
            sql.append("select t from Operator t join t.roleList r where (1=1)");
        }

        if (StringUtils.isNotBlank(qloginName)) {
            queryParams.put("loginName", "%" + this.qloginName + "%");
            sql.append(" and (t.loginName like:loginName)");
        }

        if (qroleId != null && 0 < qroleId) {
            queryParams.put("roleId", qroleId);
            sql.append(" and r.roleId =:roleId ");
        }

        Timestamp st = UtilDatetime.Date2Time(startTime);
        if (null != st) {
            queryParams.put("st", st);
            sql.append(" and t.createTime >=:st");
        }
        Timestamp et = UtilDatetime.Date2Time(endTime);
        if (null != et) {
            queryParams.put("et", et);
            sql.append(" and t.createTime <=:et");
        }
        sql.append(" order by t.createTime desc");
        return sql.toString();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    public Long getQroleId() {
        return qroleId;
    }

    public void setQroleId(Long qroleId) {
        this.qroleId = qroleId;
    }

    public String getQloginName() {
        return qloginName;
    }

    public void setQloginName(String qloginName) {
        this.qloginName = qloginName;
    }

}
