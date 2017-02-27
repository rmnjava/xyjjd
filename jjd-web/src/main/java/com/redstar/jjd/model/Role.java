package com.redstar.jjd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.redstar.jjd.utils.ShowParam;

/**
 * 
 * ClassName: Role <br/>
 * Date: 2016年6月20日 下午2:43:26 <br/>
 * Description: 系统角色 <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class Role implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 1880318789593648030L;
    /**
     * 获取系统角色ID.
     * 
     * @return 系统角色ID
     */
    @Id
    @GeneratedValue(generator = "roleSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "roleSequence", sequenceName = "SEQ_ROLE")
    @Column(name = "ROLE_ID")
    private Long roleId;
    /**
     * 获取角色名称.
     * 
     * @return 角色名称
     */
    @Column(name = "ROLE_NAME", length = 20)
    private String roleName;

    /**
     * 获取角色创建时间.
     * 
     * @return 角色名称
     */
    @Column(name = "CREATE_TIME", length = 6)
    private Timestamp createTime;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    @Column(name = "IS_DELETE")
    private String isDelete;

    /**
     * 获取操作权限集合.
     * 
     * @return 操作权限集合
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "T_SYS_R2P", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Permission> permissions = new ArrayList<Permission>();

    public List<Permission> getPermissions() {
        return permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称.
     * 
     * @param roleName
     *            角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 设置角色操作权限集合.
     * 
     * @param permssions
     *            操作权限集合
     * @param permissions
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置系统角色ID.
     * 
     * @param roleId
     *            系统角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @ShowParam(value = "操作权限", width = 500)
    @Transient
    public String getPermissionNames() {
        List<String> permissionNameList = Lists.newArrayList();
        for (Permission permission : getPermissions()) {
            if (permissionNameList.contains(permission.getResCnName()))
                continue;
            permissionNameList.add(permission.getResCnName());
        }
        return StringUtils.join(permissionNameList, ",");
    }

    /**
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
