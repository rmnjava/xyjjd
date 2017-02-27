/**
 * Project Name:jjd-web
 * File Name:StoreServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年9月22日下午4:42:35
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.redstar.jjd.admin.service.StoreService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.dao.CityDao;
import com.redstar.jjd.dao.ProvinceDao;
import com.redstar.jjd.dao.StoreDao;
import com.redstar.jjd.dao.StoreProductDao;
import com.redstar.jjd.model.City;
import com.redstar.jjd.model.Province;
import com.redstar.jjd.model.Store;
import com.redstar.jjd.model.StoreProduct;
import com.redstar.jjd.query.StoreQuery;
import com.redstar.jjd.service.impl.AbstractBasicService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.StoreView;

/**
 * ClassName: StoreServiceImpl <br/>
 * Date: 2016年9月22日 下午4:42:35 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("storeService")
public class StoreServiceImpl extends AbstractBasicService implements
        StoreService {
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private CityDao cityDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private StoreProductDao storeProductDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<StoreView> query(StoreQuery storeQuery) {
        List<StoreView> storeList = new ArrayList<StoreView>();
        List<Object[]> list = this.pageSQLQuery(storeQuery);
        storeList = covertToView(list);
        return storeList;
    }

    @Override
    public Store save(StoreView view) {
        Store store = new Store();
        ConvertUtils.convertObject(view, store);
        if (view.getId() != null) {
            return storeDao.update(store);
        } else {
            return storeDao.save(store);
        }
    }

    @Override
    public int delBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if (!id.isEmpty()) {
                // 判断该商场是否已匹配金融机构
                List<StoreProduct> list = storeProductDao.findBy("storeId",
                        Long.valueOf(id));
                if (!list.isEmpty()) {
                    return -1;
                } else {
                    storeDao.delete(Long.parseLong(id));
                }

            }
        }
        return idArr.length;
    }

    @Override
    public List<Store> findBy(String storeName) {
        return storeDao.findBy("name", storeName);
    }

    @Override
    public List<Store> findAll() {
        return storeDao.listAll();
    }

    @Override
    public Store findBy(Long storeId) {
        return storeDao.get(storeId);
    }

    /**
     * 
     * @description object对象转为UserInfoView <br/>
     * 
     * @param list
     * @return List<UserInfoView>
     * @throws
     */
    private List<StoreView> covertToView(List<Object[]> list) {
        List<StoreView> viewList = new ArrayList<StoreView>();
        for (Object[] object : list) {
            StoreView view = new StoreView();
            view.setId(object[0] == null ? null : Long.parseLong(object[0]
                    .toString()));
            view.setName(object[1] == null ? null : (String) (object[1]));
            view.setAddress(object[2] == null ? null : (String) (object[2]));
            view.setCityName(object[3] == null ? null : (String) (object[3]));
            view.setProvinceName(object[4] == null ? null
                    : (String) (object[4]));
            view.setCityCode(object[5] == null ? null : (String) (object[5]));
            view.setProvinceCode(object[6] == null ? null
                    : (String) (object[6]));
            viewList.add(view);
        }
        return viewList;

    }

    @Override
    public List<ComboVO> getStoresByCity(String cityCode) {
        List<Store> citys = storeDao.findBy("cityCode", cityCode);
        return convertComboVoList(citys, "id", "name");
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
    private List<ComboVO> convertComboVoList(List<Store> list, String id,
            String propertyName) {
        List<ComboVO> retComboVos = Lists.newArrayList();
        ComboVO defaultComboVo = new ComboVO(null,
                GeneralConstant.DEFAULT_COMBOVO);
        retComboVos.add(defaultComboVo);
        for (Store obj : list) {
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

    @Override
    public StoreView findByStoreId(Long storeId) {
        Store store = storeDao.get(storeId);
        StoreView view = new StoreView();
        ConvertUtils.convertObject(store, view);
        City city = cityDao.findUniqueBy("code", view.getCityCode());
        Province province = provinceDao.findUniqueBy("code",
                city.getProvinceCode());
        view.setProvinceCode(city.getProvinceCode());
        view.setProvinceName(province.getName());
        return view;
    }
}
