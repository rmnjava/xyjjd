/**
 * Project Name:jjd-background
 * File Name:NewsView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2015年12月24日下午2:10:57
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: NewsView <br/>
 * Date: 2015年12月24日 下午2:10:57 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class NewsView {

    private Long id;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 类型
     */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
