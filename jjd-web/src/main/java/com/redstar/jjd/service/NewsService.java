/**
 * Project Name:jjd-background
 * File Name:NewsService.java
 * Package Name:com.redstar.jjd.service
 * Date:2015年12月24日下午2:07:29
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import java.util.List;

import com.redstar.jjd.vo.NewsView;

/**
 * ClassName: NewsService <br/>
 * Date: 2015年12月24日 下午2:07:29 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface NewsService {
    public List<NewsView> getNews(String type);
}
