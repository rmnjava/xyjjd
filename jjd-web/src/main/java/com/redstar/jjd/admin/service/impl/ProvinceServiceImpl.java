/**
 * Project Name:jjd-web
 * File Name:ProvinceService.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年9月26日下午1:37:34
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.service.ProvinceService;
import com.redstar.jjd.common.service.CommonService;
import com.redstar.jjd.dao.ProvinceDao;
import com.redstar.jjd.model.Province;
import com.redstar.jjd.vo.ComboVO;
import com.redstar.jjd.vo.ProvinceView;

/**
 * ClassName: ProvinceService <br/>
 * Date: 2016年9月26日 下午1:37:34 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("provinceService")
public class ProvinceServiceImpl extends CommonService<Province, ProvinceView>
        implements ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public List<ComboVO> getProvinceOptions() {
        List<Province> provinces = provinceDao.listAll();
        return super.convertComboVoList(provinces, "code", "name");
    }

}
