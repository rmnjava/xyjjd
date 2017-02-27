package com.redstar.jjd.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.StoreProductDao;
import com.redstar.jjd.model.StoreProduct;

@Repository("storeProductDao")
public class StoreProductDaoImpl extends BaseDaoImpl<StoreProduct, Long>
        implements StoreProductDao {

    @Override
    public StoreProduct findByStoreAndProduct(Long storeId, Long productId) {
        String hql = "from StoreProduct where storeId = :storeId and productId =:productId ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("storeId", storeId);
        query.setParameter("productId", productId);
        if (!query.list().isEmpty()) {
            return (StoreProduct) query.list().get(0);
        }
        return null;
    }
}
