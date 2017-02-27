/**
 * Project Name:jjd-background
 * File Name:CityDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2015年12月22日上午9:27:57
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.Store;

/**
 * ClassName: CityDao <br/>
 * Date: 2015年12月22日 上午9:27:57 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface StoreDao extends BaseDao<Store, Long> {

    public List<Object[]> getStoresByCity(String cityCode);

    public List<Store> getStores();

}
