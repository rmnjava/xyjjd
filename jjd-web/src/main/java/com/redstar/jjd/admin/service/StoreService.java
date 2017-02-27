/**
 * Project Name:jjd-web
 * File Name:StoreService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年9月22日下午4:36:07
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.util.List;

import com.redstar.jjd.model.Store;
import com.redstar.jjd.query.StoreQuery;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.StoreView;

/**
 * ClassName: StoreService <br/>
 * Date: 2016年9月22日 下午4:36:07 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface StoreService {
    public List<StoreView> query(StoreQuery storeQuery);

    public Store save(StoreView view);

    public int delBatch(String ids);

    public List<Store> findBy(String storeName);

    public List<Store> findAll();

    public Store findBy(Long storeId);

    /**
     * 根据城市获取商场下拉框
     * 
     * @return
     */
    public List<ComboVO> getStoresByCity(String cityCode);

    /**
     * 
     * @description 根据storeId获取省，城市等信息 <br/>
     * 
     * @param storeId
     * @return StoreView
     * @throws
     */
    public StoreView findByStoreId(Long storeId);

}
