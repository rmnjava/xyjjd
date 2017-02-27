/**
 * Project Name:jjd-background
 * File Name:ProvinceDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2015年12月23日上午11:18:00
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.Province;

/**
 * ClassName: ProvinceDao <br/>
 * Date: 2015年12月23日 上午11:18:00 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface ProvinceDao extends BaseDao<Province, Long> {

    public List<Object[]> getProvinces();

}
