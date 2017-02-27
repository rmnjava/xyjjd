/**
 * Project Name:jjd-background
 * File Name:News.java
 * Package Name:com.redstar.jjd.model
 * Date:2015年12月24日上午11:01:42
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ClassName: News <br/>
 * Date: 2015年12月24日 上午11:01:42 <br/>
 * Description: 广告实体类
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {

    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -7126625296938542772L;

    @Id
    @SequenceGenerator(name = "newsSequence", sequenceName = "SEQ_NEWS")
    @GeneratedValue(generator = "newsSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 图片路径
     */
    @Column(name = "IMG")
    private String img;

    /**
     * 是否有链接
     */
    @Column(name = "IS_LINK")
    private String isLink;

    /**
     * 链接地址
     */
    @Column(name = "LINK_URL")
    private String linkUrl;

    /**
     * 类型
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "CREATE_USER_ID")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsLink() {
        return isLink;
    }

    public void setIsLink(String isLink) {
        this.isLink = isLink;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
