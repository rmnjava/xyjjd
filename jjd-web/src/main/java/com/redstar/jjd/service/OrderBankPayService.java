/**
 * Project Name:jjd-background
 * File Name:OrderBankPayService.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年4月19日下午4:23:44
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import com.redstar.jjd.model.OrderBankPay;

/**
 * ClassName: OrderBankPayService <br/>
 * Date: 2016年4月19日 下午4:23:44 <br/>
 * Description: 银行支付service
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface OrderBankPayService {

    public OrderBankPay getByApplyId(Long applyId);
}
