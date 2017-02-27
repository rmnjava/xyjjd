/**
 * Project Name:jjd-background
 * File Name:NewsDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2015年12月24日上午11:16:50
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.News;

/**
 * ClassName: NewsDao <br/>
 * Date: 2015年12月24日 上午11:16:50 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface NewsDao extends BaseDao<News, Long> {
    public List<News> getNews(String type);
}
