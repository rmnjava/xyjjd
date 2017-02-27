/**
 * Project Name:jjd-web
 * File Name:CityService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年9月26日上午11:17:49
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.util.List;

import com.redstar.jjd.vo.ComboVO;

/**
 * ClassName: CityService <br/>
 * Date: 2016年9月26日 上午11:17:49 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface CityService {
    /**
     * 根据省获取城市下拉框
     * 
     * @return
     */
    public List<ComboVO> getCitysByProvince(String provinceCode);
}
