/**
 * Project Name:jjd-weixin
 * File Name:UserPayDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年8月3日下午2:45:22
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.UserPayDao;
import com.redstar.jjd.model.UserPay;
import com.redstar.jjd.query.UserPayQuery;

/**
 * ClassName: UserPayDaoImpl <br/>
 * Date: 2016年8月3日 下午2:45:22 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("userPayDao")
public class UserPayDaoImpl extends BaseDaoImpl<UserPay, Long> implements
        UserPayDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserPay> queryExport(UserPayQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t from UserPay t where 1=1 ");
        if (null != userQuery.getqStartTime()
                && !"".equals(userQuery.getqStartTime())) {
            queryParams.put("qStartTime", userQuery.getStartTime());
            sql.append(" and (t.applyDate >= :qStartTime) ");
        }
        if (null != userQuery.getqEndTime()
                && !"".equals(userQuery.getqEndTime())) {
            queryParams.put("qEndTime", userQuery.getEndTime());
            sql.append(" and (t.applyDate <= :qEndTime)");
        }
        sql.append(" and t.bankPayResult=1 order by t.applyDate desc");
        Query query = sessionFactory.getCurrentSession().createQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<UserPay>) query.list();
    }

}
