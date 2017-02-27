package com.redstar.jjd.dao;

import com.redstar.jjd.model.BankProduct;

/**
 * Created by Pengfei on 2015/12/21.
 */
public interface BankProductDao extends BaseDao<BankProduct, Long> {
    public BankProduct findByBankId(Long bankId);
}
