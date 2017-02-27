package com.redstar.jjd.utils;

import java.util.List;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;

/**
 * 
 * ClassName: ConvertUtils <br/>
 * Date: 2015年12月18日 上午10:30:47 <br/>
 * Description: dozer转换工具类 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
public class ConvertUtils {
    private static DozerBeanMapper map = new DozerBeanMapper();

    /**
     * 将对象转换为需要的类型
     * 
     * @param src
     *            传入对象
     * @param clazz
     *            要转换的类型
     * @return
     */
    public static <T> T getObject(Object src, Class<T> clazz) {
        if (src == null)
            return null;
        return (T) map.map(src, clazz);
    }

    /**
     * domain对象与vo对象互相转换
     * 
     * @param src
     *            转换值的对象
     * @param target
     *            要接收值的对象
     */
    public static void convertObject(Object src, Object target) {
        map.map(src, target);
    }

    /**
     * domain集合转换为vo集合
     * 
     * @param src
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> convertList(List src, Class<T> clazz) {
        List<T> targetList = Lists.newArrayList();
        if (src != null) {
            for (Object objSrc : src) {
                Object objTarget = getObject(objSrc, clazz);
                targetList.add((T) objTarget);
            }
        }
        return targetList;
    }
}
