/**
 * Project Name:jjd-web
 * File Name:StoreProductService.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年10月10日下午3:28:13
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.model.StoreProduct;
import com.redstar.jjd.query.StoreProductQuery;
import com.redstar.jjd.vo.StoreProductView;

/**
 * ClassName: StoreProductService <br/>
 * Date: 2016年10月10日 下午3:28:13 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface StoreProductService {

    public List<StoreProductView> queryStoreProduct(StoreProductQuery query);

    public StoreProduct save(StoreProductView view);

    public int delete(Long id);

    public StoreProductView findBy(Long id);

    public List<StoreProductView> findByStoreId(Long storeId);

    /**
     * 
     * @description 金融机构导出列表 <br/>
     * 
     * @param storeProductQuery
     * @return List<StoreProductView>
     * @throws
     */
    public List<StoreProductView> queryExportList(
            StoreProductQuery storeProductQuery);

    /**
     * 
     * @description 金融机构导出列表 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForStore(OutputStream out,
            List<StoreProductView> list);
}
