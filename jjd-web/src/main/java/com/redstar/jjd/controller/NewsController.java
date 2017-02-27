/**
 * Project Name:jjd-background
 * File Name:NewsController.java
 * Package Name:com.redstar.jjd.controller
 * Date:2015年12月24日下午2:08:31
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.service.NewsService;
import com.redstar.jjd.vo.NewsView;

/**
 * ClassName: NewsController <br/>
 * Date: 2015年12月24日 下午2:08:31 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private NewsService newsService;

    /**
     * 
     * @description 获取首页banner信息 <br/>
     * 
     * @return Map<String,Object>
     * @throws
     */
    @RequestMapping(value = "/getBanners", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBanners(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<NewsView> list = newsService.getNews(GeneralConstant.NEWS_TYPE_BANNER);
        for (NewsView view : list) {
            String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + view.getImg();
            view.setImg(imgPath);
        }
        result.put("banners", list);
        return result;
    }
}
