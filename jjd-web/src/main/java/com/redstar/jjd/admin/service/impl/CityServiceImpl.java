/**
 * Project Name:jjd-web
 * File Name:CityServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年9月26日下午1:38:39
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.service.CityService;
import com.redstar.jjd.common.service.CommonService;
import com.redstar.jjd.dao.CityDao;
import com.redstar.jjd.model.City;
import com.redstar.jjd.vo.CityView;
import com.redstar.jjd.vo.ComboVO;

/**
 * ClassName: CityServiceImpl <br/>
 * Date: 2016年9月26日 下午1:38:39 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("cityService")
public class CityServiceImpl extends CommonService<City, CityView> implements
        CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<ComboVO> getCitysByProvince(String provinceCode) {
        List<City> citys = cityDao.findBy("provinceCode", provinceCode);
        return super.convertComboVoList(citys, "code", "name");
    }
}
