package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.BankProductReturnMethod;

public interface BankProductReturnMethodDao extends
        BaseDao<BankProductReturnMethod, Long> {
    List<BankProductReturnMethod> getByProductIDAndIsZero(
            List<Long> productIdList, Boolean isZero);

    public List<BankProductReturnMethod> findByProductId(Long productId);
}
