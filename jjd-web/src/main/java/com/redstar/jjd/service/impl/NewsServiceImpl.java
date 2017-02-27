/**
 * Project Name:jjd-background
 * File Name:NewsServiceImpl.java
 * Package Name:com.redstar.jjd.service.impl
 * Date:2015年12月24日下午2:07:42
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.dao.NewsDao;
import com.redstar.jjd.model.News;
import com.redstar.jjd.service.NewsService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.NewsView;

/**
 * ClassName: NewsServiceImpl <br/>
 * Date: 2015年12月24日 下午2:07:42 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<NewsView> getNews(String type) {
        List<News> news = newsDao.getNews(type);
        List<NewsView> list = ConvertUtils.convertList(news, NewsView.class);
        return list;
    }
}
