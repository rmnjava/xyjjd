package com.redstar.jjd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.redstar.jjd.query.AbstractPagedQuery;

/**
 * Created by Pengfei on 2015/11/26. Base interface for all DAO
 */
public interface BaseDao<T, PK extends Serializable> {
    T save(T model);

    T saveOrUpdate(T model);

    T update(T model);

    int delete(PK id);

    List<T> listAll();

    T get(PK id);

    /**
     * 按Criteria查询对象列表.
     * 
     * @param criterions
     */
    List<T> findList(final Criterion... criterions);

    /**
     * 按属性查找对象列表, 匹配方式为相等.
     */
    List<T> findBy(final String propertyName, final Object value);

    /**
     * 按属性查找唯一对象, 匹配方式为相等.
     */
    T findUniqueBy(final String propertyName, final Object value);

    /**
     * 分页查询
     * 
     * @param query
     * @return List
     */
    List<T> pageQuery(AbstractPagedQuery<T> page);
}
