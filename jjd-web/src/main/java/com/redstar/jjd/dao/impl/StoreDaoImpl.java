/**
 * Project Name:jjd-background
 * File Name:CityDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2015年12月22日上午9:29:08
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.StoreDao;
import com.redstar.jjd.model.Store;

/**
 * ClassName: CityDaoImpl <br/>
 * Date: 2015年12月22日 上午9:29:08 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store, Long> implements StoreDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getStoresByCity(String cityCode) {
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct(s.id) as storeId,s.name as storeName,s.cityCode,s.address ");
        sql.append(" from StoreProduct sp,Store s,Province p,City c ");
        sql.append(" where sp.storeId=s.id and s.cityCode=c.code and c.provinceCode=p.code");
        sql.append(" and c.code=:cityCode");
        Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
        query.setParameter("cityCode", cityCode);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Store> getStores() {
        StringBuilder sql = new StringBuilder();
        sql.append(" from StoreProduct sp,Store s ");
        sql.append(" where sp.storeId=s.id ");
        Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
        return query.list();
    }

}
