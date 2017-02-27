package com.redstar.jjd.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */

@Entity
@Table(name = "T_SYS_MENU")
public class Menu {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "MENU_ID", nullable = false)
    private Long menuId;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME", length = 50)
    private String menuName;

    /**
     * 菜单别名
     */
    @Column(name = "ALIAS_NAME", length = 50)
    private String aliasName;

    /**
     * 菜单对应URL
     */
    @Column(name = "MENU_URL")
    private String menuURL;

    /**
     * 菜单序号
     */
    @Column(name = "ORDER_NUM")
    private Integer orderNum;
    /**
     * 下级菜单
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy("orderNum")
    @JsonIgnore
    private Set<Menu> children = new LinkedHashSet<Menu>();
    /**
     * 上级菜单
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID")
    private Menu parent;

    /**
     * 权限集合
     * */
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    @JsonIgnore
    private Set<Permission> permissions = new HashSet<Permission>();

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @JsonManagedReference
    public Menu getParent() {
        return parent;
    }

    @JsonManagedReference
    public Set<Menu> getChildren() {
        return children;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}
