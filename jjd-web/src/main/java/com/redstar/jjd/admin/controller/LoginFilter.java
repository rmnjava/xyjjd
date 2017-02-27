package com.redstar.jjd.admin.controller;

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

public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginFilter.class);

    private String excludedPages; // 存储web.xml里面配置的filter的init-param的init-value
    private String[] pages; // excludedPages调用split方法后的String[]

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // HttpServletRequest hsq = (HttpServletRequest) req;
        // Operator u = (Operator) hsq.getSession().getAttribute("loginUser");
        // if (u == null) {
        // ((HttpServletResponse) resp).sendRedirect(hsq.getContextPath()
        // + "/login");
        // }
        // chain.doFilter(req, resp);

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

        // String method = req.getParameter("method");
        // 退出
        // String url = req.getServletPath();
        // if (url.equals("logout")) {
        // isExcludedPage = true;
        // }

        if (isExcludedPage) {
            chain.doFilter(request, response);
            LOGGER.info("This page does not need to filter.");
        } else if (session.getAttribute("loginUser") != null) {// 登录后才能访问
            chain.doFilter(request, response);
            LOGGER.info("you are logged in.");
        } else {
            res.sendRedirect(path + "/index.jsp");
            LOGGER.info("fail,login required!");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages = filterConfig.getInitParameter("excludedPages"); // 获取init-value
        pages = excludedPages.split(","); // 使用,号分割excluded pages
    }

}
