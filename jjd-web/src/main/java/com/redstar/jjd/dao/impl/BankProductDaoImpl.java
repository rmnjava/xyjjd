package com.redstar.jjd.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.BankProductDao;
import com.redstar.jjd.model.BankProduct;

@Repository("bankProductDao")
public class BankProductDaoImpl extends BaseDaoImpl<BankProduct, Long>
        implements BankProductDao {
    @Override
    public BankProduct findByBankId(Long bankId) {
        String hql = "from BankProduct where bankId = :bankId order by endTime desc ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("bankId", bankId);
        if (!query.list().isEmpty()) {
            return (BankProduct) query.list().get(0);
        }
        return null;
    }

}
