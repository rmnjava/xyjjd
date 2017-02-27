/**
 * Project Name:jjd-web
 * File Name:BankService.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年9月5日上午10:54:58
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import java.util.List;

import com.redstar.jjd.model.Bank;
import com.redstar.jjd.vo.ComboVO;

/**
 * ClassName: BankService <br/>
 * Date: 2016年9月5日 上午10:54:58 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface BankService {

    public List<Bank> findAll(String hasBankPay);

    /**
     * 
     * @description 根据商场查询该商场可以添加的金融机构 <br/>
     * 
     * @param storeId
     * @return List<Bank>
     * @throws
     */
    public List<Bank> findBanksByStore(Long storeId);

    /**
     * 根据商场获取金融机构下拉框
     * 
     * @return
     */
    public List<ComboVO> getBanksByStoreId(Long storeId);

    public Bank findBy(Long bankId);

    public List<Bank> findByName(String bankName);
}
