/**
 * Project Name:jjd-web
 * File Name:LoginFilter.java
 * Package Name:com.redstar.jjd.common
 * Date:2016年6月13日下午3:20:29
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: LoginFilter <br/>
 * Date: 2016年6月13日 下午3:20:29 <br/>
 * Description: 登陆过滤器
 * 
 * @author huangrui
 * @version
 * @see
 */
public class LoginFilter2 implements Filter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginFilter2.class);

    private String excludedPages; // 存储web.xml里面配置的filter的init-param的init-value
    private String[] pages; // excludedPages调用split方法后的String[]

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages = filterConfig.getInitParameter("excludedPages"); // 获取init-value
        pages = excludedPages.split(","); // 使用,号分割excluded pages

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getContextPath();// 取得根目录
        LOGGER.info("requestPath :" + req.getContextPath()
                + req.getServletPath());
        HttpSession session = req.getSession();
        boolean isExcludedPage = false;

        for (int i = 0; i < pages.length; i++) {

            // 判断请求的页面是否excluded page
            if (req.getServletPath().equals(pages[i])) {
                isExcludedPage = true;
                break;
            }
        }

        if (isExcludedPage) {
            chain.doFilter(request, response);
            LOGGER.info("This page does not need to filter.");
        } else if (session.getAttribute("userNo") != null) {// 登录后才能访问
            chain.doFilter(request, response);
            LOGGER.info("you are logged in.");
        } else {
            res.sendRedirect(path + "/index.jsp");
            LOGGER.info("fail,login required!");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
