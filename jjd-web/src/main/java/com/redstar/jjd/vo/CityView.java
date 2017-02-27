/**
 * Project Name:jjd-background
 * File Name:CityView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2015年12月23日下午2:13:13
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.util.List;

import com.redstar.jjd.model.Store;

/**
 * ClassName: CityView <br/>
 * Date: 2015年12月23日 下午2:13:13 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class CityView {
    private String cityName;
    private List<Store> stores;
    private String cityCode;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
