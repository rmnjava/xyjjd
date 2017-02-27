package com.redstar.jjd.dao;

import com.redstar.jjd.model.StoreProduct;

public interface StoreProductDao extends BaseDao<StoreProduct, Long> {

    public StoreProduct findByStoreAndProduct(Long storeId, Long productId);
}
