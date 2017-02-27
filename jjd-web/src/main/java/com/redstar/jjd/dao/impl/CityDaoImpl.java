/**
 * Project Name:jjd-background
 * File Name:CityDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2015年12月23日上午10:38:15
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.CityDao;
import com.redstar.jjd.model.City;

/**
 * ClassName: CityDaoImpl <br/>
 * Date: 2015年12月23日 上午10:38:15 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl<City, Long> implements CityDao {

    @Override
    public List<City> getCitysByProvince(String provinceCode) {
        String sql = "from City where provinceCode=:provinceCode and state=1";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("provinceCode", provinceCode);
        return query.list();
    }

    @Override
    public List<Object[]> getCitys(String provinceCode) {
        StringBuilder sql = new StringBuilder();
        sql.append("select c.code,c.name ");
        sql.append(" from StoreProduct sp,Store s,Province p,City c ");
        sql.append(" where sp.storeId=s.id and s.cityCode=c.code and c.provinceCode=p.code and p.code=:provinceCode ");
        sql.append(" group by c.code,c.name ");
        Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
        query.setParameter("provinceCode", provinceCode);
        return query.list();
    }

}
