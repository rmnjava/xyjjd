package com.redstar.jjd.query;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.redstar.jjd.model.Role;
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
public class RoleQuery extends AbstractPagedQuery<Role> {

    /**
     * 角色Id
     * */
    private Long roleId;
    private String roleName;
    private String startTime;
    private String endTime;

    @Override
    public String buildSQL(HashMap<String, Object> queryParams,
            boolean isQueryTotalCount) {
        StringBuilder sql = new StringBuilder();

        if (isQueryTotalCount) {// 查询总数
            sql.append("select count(*) from Role t where (1=1)");
        } else {
            sql.append("select t from Role t where (1=1)");
        }

        if (StringUtils.isNotBlank(roleName)) {
            queryParams.put("roleName", "%" + this.roleName + "%");
            sql.append(" and (t.roleName like:roleName)");
        }
        // Timestamp st = UtilDatetime.Date2Time(startTime);
        // if (null != st) {
        // queryParams.put("st", st);
        // sql.append(" and t.createTime >=:st");
        // }
        // Timestamp et = UtilDatetime.Date2Time(endTime);
        // if (null != et) {
        // queryParams.put("et", et);
        // sql.append(" and t.createTime <=:et");
        // }

        if (null != startTime && !"".equals(startTime)) {
            queryParams.put("startTime", getStartTimeStamp());
            sql.append(" and (t.createTime >= :startTime) ");
        }
        if (null != endTime && !"".equals(endTime)) {
            queryParams.put("endTime", getEndTimeStamp());
            sql.append(" and (t.createTime <= :endTime)");
        }
        sql.append(" order by t.roleId desc");
        return sql.toString();
    }

    public Timestamp getStartTimeStamp() {
        Timestamp start = null;
        if (null != startTime && !"".equals(startTime)) {
            try {
                start = new Timestamp(UtilDatetime.getStartTimeByDay(
                        UtilDatetime.parseStringToShortDate(startTime))
                        .getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return start;
    }

    public Timestamp getEndTimeStamp() {
        Timestamp end = null;
        if (null != endTime && !"".equals(endTime)) {
            try {
                end = new Timestamp(UtilDatetime.getEndTimeByDay(
                        UtilDatetime.parseStringToShortDate(endTime)).getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return end;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
