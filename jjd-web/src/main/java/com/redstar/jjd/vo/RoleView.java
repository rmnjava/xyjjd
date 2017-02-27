package com.redstar.jjd.vo;

import java.sql.Timestamp;
import java.util.List;

import com.redstar.jjd.model.Permission;

/**
 * 
 * ClassName: RoleView <br/>
 * Date: 2016年6月23日 下午5:00:26 <br/>
 * Description: 角色vo <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
public class RoleView {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    private Timestamp createTime;

    /**
     * 操作权限集合.
     */
    private List<Permission> permissions;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
