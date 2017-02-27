/**
 * Project Name:jjd-background
 * File Name:CityDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2015年12月23日上午10:37:37
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.City;

/**
 * ClassName: CityDao <br/>
 * Date: 2015年12月23日 上午10:37:37 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface CityDao extends BaseDao<City, Long> {

    public List<City> getCitysByProvince(String provinceCode);

    public List<Object[]> getCitys(String provinceCode);

}
