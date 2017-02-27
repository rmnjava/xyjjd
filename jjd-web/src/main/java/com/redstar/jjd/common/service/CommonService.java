package com.redstar.jjd.common.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.collect.Lists;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.ComboVO;

/**
 * 通用service，包含基础的domain与view的转换方法
 * 
 * @title
 * @description
 * @usage
 * @copyright Copyright 2012 HUAXIA Corporation. All rights reserved.
 * @company
 * @author
 * @create
 */
public abstract class CommonService<Domain, View> {

    public View convert2View(Domain domain) {
        return ConvertUtils.getObject(domain,
                (Class<View>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public Domain convert2Domain(View view) {
        return ConvertUtils.getObject(view,
                (Class<Domain>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public List<View> convert2View(List<Domain> domain) {
        return ConvertUtils.convertList(domain,
                (Class<View>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public List<Domain> convert2Domain(List<View> view) {
        return ConvertUtils.convertList(view,
                (Class<Domain>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);
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
    public List<ComboVO> convertComboVoList(List<Domain> list, String id,
            String propertyName) {
        List<ComboVO> retComboVos = Lists.newArrayList();
        ComboVO defaultComboVo = new ComboVO("",
                GeneralConstant.DEFAULT_COMBOVO);
        retComboVos.add(defaultComboVo);
        for (Domain obj : list) {
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
}
