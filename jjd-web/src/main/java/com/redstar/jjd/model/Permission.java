package com.redstar.jjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_SYS_PERMISSION")
public class Permission {

    @Id
    @GeneratedValue
    @Column(name = "PERMISSION_ID")
    private Long permissionId;
    /**
     * 模块名称
     */
    @Column(name = "RES_NAME", length = 50)
    private String resName;

    /**
     * 模块中文名
     */
    @Column(name = "RES_CN_NAME", length = 50)
    private String resCnName;

    /**
     * 操作方式
     */
    @Column(name = "OPERATION", length = 50)
    private String operation;

    /**
     * 操作方式名称
     */
    @Column(name = "OPERATION_CN_NAME", length = 50)
    private String operationCnName;

    /**
     * 对应菜单
     */
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    @JsonBackReference
    @JsonIgnore
    private Menu menu;

    public String getResName() {
        return resName;
    }

    public String getOperation() {
        return operation;
    }

    public String getResCnName() {
        return resCnName;
    }

    @Transient
    public String getShiroPerm() {
        return this.getResName() + ":" + this.getOperation();
    }

    public void setResCnName(String resCnName) {
        this.resCnName = resCnName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @JsonBackReference
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getOperationCnName() {
        return operationCnName;
    }

    public void setOperationCnName(String operationCnName) {
        this.operationCnName = operationCnName;
    }

}
