/**
 * Project Name:jjd-web
 * File Name:StoreView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年9月22日下午4:47:52
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: StoreView <br/>
 * Date: 2016年9月22日 下午4:47:52 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class StoreView {
    private Long id;
    private String cityCode;
    private String name;
    private String address;

    private String cityName;

    private String provinceName;

    private String provinceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
