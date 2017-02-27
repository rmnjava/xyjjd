package com.redstar.jjd.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.redstar.jjd.query.AbstractPagedQuery;

/**
 * 
 * ClassName: AbstractBasicService <br/>
 * Date: 2016年6月17日 上午11:18:15 <br/>
 * Description: 利用SQL语句来分页的抽象类 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
public abstract class AbstractBasicService {

    @Autowired
    @Qualifier("sessionFactory")
    public SessionFactory sessionFactory;

    /**
     * 获取session
     * 
     * @return
     */
    public Session getSession() {
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

    /**
     * 执行SQL语句来分页的方法
     * 
     * @param page
     * @return
     */
    public List<Object[]> pageSQLQuery(AbstractPagedQuery<Object[]> page) {
        if (page.getTotalCount() == -1) {// 查询总数
            Query query = page.buildSQLQuery(this.getSession(), true);
            BigDecimal totalCount = (BigDecimal) query.uniqueResult();
            page.setTotalCount(totalCount.longValue());
        }
        Query query = page.buildSQLQuery(this.getSession(), false);
        if (page.isExportFlag()) {
            query.setFirstResult(0);
            query.setMaxResults((int) page.getTotalCount());
        } else {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getRp());
        }
        page.setQueryResults(query.list());
        return page.getQueryResults();
    }
}
