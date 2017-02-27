/**
 * Project Name:jjd-background
 * File Name:OrderPayResultDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年4月18日下午3:53:07
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.dao.OrderBankPayDao;
import com.redstar.jjd.model.OrderBankPay;

/**
 * ClassName: OrderPayResultDaoImpl <br/>
 * Date: 2016年4月18日 下午3:53:07 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("orderBankPayDao")
public class OrderBankPayDaoImpl extends BaseDaoImpl<OrderBankPay, Long>
        implements OrderBankPayDao {

    @Override
    public Long getSumOrderNumByDay() {
        String hql = "select count(id) from OrderBankPay where to_char(orderDate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd') ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (Long) query.list().get(0);
    }

    @Override
    public List<OrderBankPay> getNoPayList(String userId) {

        String sql = "from OrderBankPay where bankPayResult=:bankPayResult and userId=:userId order by orderDate desc";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("bankPayResult", GeneralConstant.BANK_PAY_RESULT_NO);
        query.setParameter("userId", userId);
        return query.list();
    }
}
