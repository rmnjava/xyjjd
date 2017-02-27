package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.BankProductReturnMethodDao;
import com.redstar.jjd.model.BankProductReturnMethod;

@Repository("bankProductReturnMethodDao")
public class BankProductReturnMethodDaoImpl extends
        BaseDaoImpl<BankProductReturnMethod, Long> implements
        BankProductReturnMethodDao {

    @SuppressWarnings("unchecked")
    @Override
    public List getByProductIDAndIsZero(List<Long> productIds, Boolean isZero) {
        String hql = "from BankProductReturnMethod where productId in (:productIds) and is_zero = :isZero";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameterList("productIds", productIds);
        query.setParameter("isZero", isZero);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BankProductReturnMethod> findByProductId(Long productId) {
        String hql = "from BankProductReturnMethod where productId = :productId order by period ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("productId", productId);
        return (List<BankProductReturnMethod>) query.list();
    }
}
