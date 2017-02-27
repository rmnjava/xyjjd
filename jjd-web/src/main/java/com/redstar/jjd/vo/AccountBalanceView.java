/**
 * Project Name:jjd-background
 * File Name:AccountBalanceView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2015年12月29日下午3:22:45
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

import java.util.Map;

/**
 * ClassName: AccountBalanceView <br/>
 * Date: 2015年12月29日 下午3:22:45 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class AccountBalanceView {
    // 商场余额
    private Map<String, Double> sumByStore;

    // 账户余额
    private Double accountBalance;

    public Map<String, Double> getSumByStore() {
        return sumByStore;
    }

    public void setSumByStore(Map<String, Double> sumByStore) {
        this.sumByStore = sumByStore;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

}
