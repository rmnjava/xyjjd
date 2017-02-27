package com.redstar.jjd.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * ClassName: LoginController <br/>
 * Date: 2016年7月12日 下午5:48:04 <br/>
 * Description: LoginAction负责打开登录页面(GET请求)和登录出错页面(POST请求)， 真正登录的POST请求由Filter完成 <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginShow() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "user.list";
        }
        return "system.login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(
            @RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String loginName,
            Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
                loginName);
        return "system.login";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/login";
    }

}
