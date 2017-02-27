package com.redstar.jjd.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This inctercept is to check auth
 * Created by Pengfei on 2015/9/18.
 */
public class AuthInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //no need to check the auth for the uri /admin/ and /admin/login
        String requestUri = request.getRequestURI();
        logger.debug("reqiestUri:::" + requestUri);

        /*User user = (User)request.getSession().getAttribute(GeneralConstant.SESSION_USER_KEY);
        if (user == null) {
            logger.info("No auth. Relogin...");
            throw new AuthException();
        }*/
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JSONUtil.stringifySingle(ControllerResult.getResult(false)));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
