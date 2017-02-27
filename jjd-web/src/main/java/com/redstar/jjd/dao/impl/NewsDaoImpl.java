/**
 * Project Name:jjd-background
 * File Name:NewsDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2015年12月24日下午1:52:34
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.NewsDao;
import com.redstar.jjd.model.News;

/**
 * ClassName: NewsDaoImpl <br/>
 * Date: 2015年12月24日 下午1:52:34 <br/>
 * Description: newsDao实现类
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements NewsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<News> getNews(String type) {
        String sql = "from News where type=:type and rownum<=3 order by createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("type", type);
        return query.list();
    }

}
