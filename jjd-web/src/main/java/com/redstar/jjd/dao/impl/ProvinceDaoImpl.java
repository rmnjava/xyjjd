/**
 * Project Name:jjd-background
 * File Name:ProvinceDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2015年12月23日上午11:18:29
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.ProvinceDao;
import com.redstar.jjd.model.Province;

/**
 * ClassName: ProvinceDaoImpl <br/>
 * Date: 2015年12月23日 上午11:18:29 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("provinceDao")
public class ProvinceDaoImpl extends BaseDaoImpl<Province, Long> implements ProvinceDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getProvinces() {
        StringBuilder sql = new StringBuilder();
        sql.append("select p.code, p.name as provinceName ");
        sql.append(" from StoreProduct sp,Store s,Province p,City c ");
        sql.append(" where sp.storeId=s.id and s.cityCode=c.code and c.provinceCode=p.code");
        sql.append(" group by p.code,p.name");
        Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
        return query.list();
    }

}
