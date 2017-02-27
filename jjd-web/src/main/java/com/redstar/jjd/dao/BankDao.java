package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.Bank;

/**
 * Created by Pengfei on 2015/12/18.
 */
public interface BankDao extends BaseDao<Bank, Long> {
    List<Bank> findByIds(List<Long> ids);

    public Object[] getByProductId(Long productId);
}
