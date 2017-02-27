/**
 * Project Name:jjd-background
 * File Name:UserDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2015年12月16日下午5:14:38
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.UserDao;
import com.redstar.jjd.model.User;
import com.redstar.jjd.utils.UtilDatetime;

/**
 * ClassName: UserDaoImpl <br/>
 * Date: 2015年12月16日 下午5:14:38 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User findByPhone(String phone) {
        User user = findUniqueBy("phone", phone);
        return user;

    }

    @Override
    public User findUser(User user) {
        Criterion phoneCriterion = Restrictions.eq("phone", user.getPhone());
        Criterion passwordCriterion = Restrictions.eq("password",
                user.getPassword());
        User u = findUnique(phoneCriterion, passwordCriterion);
        return u;
    }

    @Override
    public List<Object[]> getInfos(String start, String end) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> queryparam = new HashMap<String, Object>();
        sql.append("select t.userId,t.orderDate,t.orderNo,u.phone,info.name,info.idNumber,a.amount*10000 as sumamount,s.name,a.period,a.returnPerMonth ");
        sql.append(" from OrderBankPay t,UserApply a,User u,UserInfo info,Store s where t.applyId=a.id ");
        sql.append(" and t.userId=u.id and u.id=info.userId and a.storeId=s.id ");
        if (null != start && !"".equals(start)) {
            Timestamp startTime = new Timestamp(UtilDatetime.getStartTimeByDay(
                    UtilDatetime.parseStringToShortDate(start)).getTime());
            queryparam.put("start", startTime);
            sql.append(" and (t.orderDate >= :start)");
        }
        if (null != end && !"".equals(end)) {
            Timestamp endTime = new Timestamp(UtilDatetime.getEndTimeByDay(
                    UtilDatetime.parseStringToShortDate(end)).getTime());
            queryparam.put("end", endTime);
            sql.append(" and (t.orderDate <= :end)");
        }
        sql.append("and t.bankPayResult=1 order by t.orderDate desc");
        Query query = sessionFactory.getCurrentSession().createQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryparam.entrySet()) {
            query.setParameter(entry.getKey(), queryparam.get(entry.getKey()));
        }
        return query.list();
    }

    @Override
    public User findByPhoneAndChannel(String phone, String enterChannel) {
        Criterion phoneCriterion = Restrictions.eq("phone", phone);
        Criterion channelCriterion = Restrictions.eq("enterChannel",
                enterChannel);
        User u = findUnique(phoneCriterion, channelCriterion);
        return u;
    }

}
