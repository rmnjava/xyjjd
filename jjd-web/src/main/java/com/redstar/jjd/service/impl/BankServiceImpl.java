/**
 * Project Name:jjd-web
 * File Name:BankServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年9月5日上午10:55:27
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.BankProductDao;
import com.redstar.jjd.dao.StoreProductDao;
import com.redstar.jjd.model.Bank;
import com.redstar.jjd.model.BankProduct;
import com.redstar.jjd.model.StoreProduct;
import com.redstar.jjd.service.BankService;
import com.redstar.jjd.vo.ComboVO;

/**
 * ClassName: BankServiceImpl <br/>
 * Date: 2016年9月5日 上午10:55:27 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("bankService")
public class BankServiceImpl extends AbstractBasicService implements
        BankService {

    @Autowired
    private BankDao bankDao;
    @Autowired
    private BankProductDao bankProductDao;

    @Autowired
    private StoreProductDao storeProductDao;

    @Override
    public List<Bank> findAll(String hasBankPay) {
        List<Bank> result = new ArrayList<Bank>();
        List<Bank> list = bankDao.listAll();
        for (Bank bank : list) {
            BankProduct bankProduct = bankProductDao.findByBankId(bank.getId());
            if (bankProduct != null && ("-1".equals(hasBankPay))) {
                result.add(bank);
                continue;
            }
            if (bankProduct != null
                    && (bankProduct.getHasBankPay().toString()
                            .equals(hasBankPay))) {
                result.add(bank);
            }

        }
        return result;
    }

    @Override
    public Bank findBy(Long bankId) {
        return bankDao.get(bankId);
    }

    @Override
    public List<Bank> findByName(String bankName) {
        return bankDao.findBy("name", bankName);
    }

    @Override
    public List<Bank> findBanksByStore(Long storeId) {
        List<Bank> result = new ArrayList<Bank>();
        List<Bank> list = bankDao.listAll();
        for (Bank bank : list) {
            BankProduct bankProduct = bankProductDao.findByBankId(bank.getId());
            if (bankProduct != null) {
                StoreProduct storeProduct = storeProductDao
                        .findByStoreAndProduct(storeId, bankProduct.getId());
                if (storeProduct == null) {
                    result.add(bank);
                }

            }

        }
        return result;
    }

    @Override
    public List<ComboVO> getBanksByStoreId(Long storeId) {
        List<StoreProduct> list = storeProductDao.findBy("storeId", storeId);
        List<Bank> banks = new ArrayList<Bank>();
        for (StoreProduct product : list) {
            Bank bank = new Bank();
            Object object[] = bankDao.getByProductId(product.getProductId());
            bank.setId(Long.valueOf(object[0].toString()));
            bank.setName((String) object[1]);
            banks.add(bank);
        }
        return convertComboVoList(banks, "id", "name");
    }

    /***
     * 下拉菜单参数转化类
     * 
     * @param list
     *            要转化的集合
     * @param id
     *            主键Id
     * @param propertyName
     *            要展现的属性
     * @return
     */
    private List<ComboVO> convertComboVoList(List<Bank> list, String id,
            String propertyName) {
        List<ComboVO> retComboVos = Lists.newArrayList();
        ComboVO defaultComboVo = new ComboVO(null,
                GeneralConstant.DEFAULT_COMBOVO);
        retComboVos.add(defaultComboVo);
        for (Bank obj : list) {
            ComboVO tempVo = null;
            if (null != obj && StringUtils.isNotBlank(id)
                    && StringUtils.isNotBlank(propertyName)) {
                try {
                    tempVo = new ComboVO(FieldUtils.readField(obj, id, true)
                            .toString(), FieldUtils.readField(obj,
                            propertyName, true).toString());
                    retComboVos.add(tempVo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retComboVos;
    }
}
