/**
 * Project Name:jjd-web
 * File Name:ProvinceService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年9月26日上午11:17:38
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import java.util.List;

import com.redstar.jjd.vo.ComboVO;

/**
 * ClassName: ProvinceService <br/>
 * Date: 2016年9月26日 上午11:17:38 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface ProvinceService {
    // 获取全部省下拉选项
    public List<ComboVO> getProvinceOptions();
}
