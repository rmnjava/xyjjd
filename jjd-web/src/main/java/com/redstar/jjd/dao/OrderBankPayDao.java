/**
 * Project Name:jjd-background
 * File Name:OrderPayResultDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2016年4月18日下午3:52:08
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.OrderBankPay;

/**
 * ClassName: OrderBankPayDao <br/>
 * Date: 2016年4月18日 下午3:52:08 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface OrderBankPayDao extends BaseDao<OrderBankPay, Long> {
    // 获取当天订单数
    public Long getSumOrderNumByDay();

    // 根据用户id查询待支付订单
    public List<OrderBankPay> getNoPayList(String userId);

}
