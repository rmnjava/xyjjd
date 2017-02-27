package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.model.Bank;

/**
 * Created by Pengfei on 2015/12/21.
 */
@Repository("bankDao")
public class BankDaoImpl extends BaseDaoImpl<Bank, Long> implements BankDao {
    @Override
    public List<Bank> findByIds(List<Long> ids) {
        String hql = "from Bank where id in (:ids)";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameterList("ids", ids);
        return query.list();
    }

    @Override
    public Object[] getByProductId(Long productId) {
        String sql = "select t.ID as bankId,t.NAME as bankName from T_BANK t, T_BANK_PRODUCT p where t.ID=P.BANK_ID and p.ID = ?";
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        query.setLong(0, productId);
        return (Object[]) query.list().get(0);
    }
}
