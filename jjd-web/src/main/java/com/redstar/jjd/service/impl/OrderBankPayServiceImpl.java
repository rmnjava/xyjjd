/**
 * Project Name:jjd-background
 * File Name:OrderBankPayServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2016年4月19日下午4:23:59
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redstar.jjd.dao.OrderBankPayDao;
import com.redstar.jjd.model.OrderBankPay;
import com.redstar.jjd.service.OrderBankPayService;

/**
 * ClassName: OrderBankPayServiceImpl <br/>
 * Date: 2016年4月19日 下午4:23:59 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("orderBankPayService")
@Transactional
public class OrderBankPayServiceImpl implements OrderBankPayService {

    @Autowired
    private OrderBankPayDao orderBankPayDao;

    @Override
    public OrderBankPay getByApplyId(Long applyId) {
        OrderBankPay order = orderBankPayDao.findUniqueBy("applyId", applyId);
        return order;
    }

}
