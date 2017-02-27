package com.redstar.jjd.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.redstar.jjd.dao.BaseDao;
import com.redstar.jjd.query.AbstractPagedQuery;

/**
 * Created by Pengfei on 2015/11/26. Base interface for all DAO implementation
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
    /**
     * 实体Class.
     */
    private final Class<T> clazz;
    @Autowired
    protected SessionFactory sessionFactory;

    public Session getSession() {
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

    /**
     * 构造函数，确定实体类型.
     */
    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T model) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(model);
        session.flush();
        return model;
    }

    @Override
    public T saveOrUpdate(T model) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(model);
        session.flush();
        return model;
    }

    @Override
    public T update(T model) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(model);
        session.flush();
        return model;
    }

    @Override
    public int delete(PK id) {
        String hql = "delete from " + clazz.getSimpleName() + " where id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public List<T> listAll() {
        String hql = "from " + clazz.getSimpleName();
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public T get(PK id) {
        return (T) this.sessionFactory.getCurrentSession().get(clazz, id);
    }

    protected List<T> listPage(int page, int size, String hql,
            List<Object> parameters) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        int i = 0;
        for (Object param : parameters) {
            query.setParameter(i, param);
            i++;
        }
        query.setFirstResult(page);
        query.setMaxResults(size);
        return query.list();
    }

    protected int getCount(String hql, List<Object> parameters) {
        hql = "select count 1 " + hql;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        int i = 0;
        for (Object param : parameters) {
            query.setParameter(i, param);
            i++;
        }
        return Integer.valueOf(query.uniqueResult().toString());
    }

    /**
     * 根据Criterion条件创建Criteria.
     * 
     * @param criterions
     */
    private Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(clazz);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 按Criteria查询对象列表.
     * 
     * @param criterions
     */
    @Override
    public List<T> findList(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 按属性查找对象列表, 匹配方式为相等.
     */
    @Override
    public List<T> findBy(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "查询属性不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return findList(criterion);
    }

    /**
     * 按属性查找唯一对象, 匹配方式为相等.
     */
    @Override
    public T findUniqueBy(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "查询属性不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) findUnique(criterion);
    }

    /**
     * 按HQL查询唯一对象.
     * 
     * @param values
     */
    public List<T> findByHql(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL查询唯一对象.
     * 
     * @param values
     */
    public T findUnique(final String hql, final Object... values) {
        return (T) createQuery(hql, values).uniqueResult();
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * @param values
     */
    private Query createQuery(final String hql, final Object... values) {
        Assert.hasText(hql, "hql不能为空");
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    /**
     * 按Criteria查询唯一对象.
     * 
     * @param criterions
     */
    public T findUnique(final Criterion... criterions) {
        return (T) createCriteria(criterions).uniqueResult();
    }

    @Override
    public List<T> pageQuery(AbstractPagedQuery<T> page) {
        if (page.getTotalCount() == -1) {// 查询总数
            Query query = page.buildQuery(this.getSession(), true);
            long totalCount = (Long) query.uniqueResult();
            page.setTotalCount(totalCount);
        }
        Query query = page.buildQuery(this.getSession(), false);
        if (page.isExportFlag()) {
            query.setFirstResult(0);
            query.setMaxResults((int) page.getTotalCount());
        } else {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getRp());
        }
        List<T> list = query.list();
        page.setQueryResults(list);
        return page.getQueryResults();
    }

}
