/**
 * Project Name:jjd-background
 * File Name:ProvinceView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2015年12月23日下午1:50:43
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.util.List;

/**
 * ClassName: ProvinceView <br/>
 * Date: 2015年12月23日 下午1:50:43 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class ProvinceView {

    private String provinceName;
    private String provinceCode;

    private List<CityView> citys;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<CityView> getCitys() {
        return citys;
    }

    public void setCitys(List<CityView> citys) {
        this.citys = citys;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

}
