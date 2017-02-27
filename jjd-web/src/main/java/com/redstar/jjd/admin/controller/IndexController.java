package com.redstar.jjd.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redstar.jjd.admin.service.OperatorService;
import com.redstar.jjd.constant.GeneralConstant;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.security.ShiroDbRealm;
import com.redstar.jjd.security.ShiroDbRealm.ShiroUser;

/**
 * 
 * ClassName: IndexAction <br/>
 * Date: 2016年7月12日 下午5:34:51 <br/>
 * Description: 首页 Controller <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "admin/index.do")
public class IndexController {
    @Autowired
    private OperatorService operatorService;

    @RequestMapping(params = { "method=list" })
    public String index(Model model, HttpServletRequest request) {
        // 判断是否是商场用户
        HttpSession session = request.getSession();
        ShiroUser shiroUser = ShiroDbRealm.getCurrentUser();
        Operator operator = operatorService.findById(shiroUser.getId());
        List<Role> roleList = operator.getRoleList();
        if (roleList != null && roleList.size() > 0) {
            Role role = roleList.get(0);// 确定了一个用户只有一个角色
            if (role.getRoleName().trim()
                    .equals(GeneralConstant.STOREROLENAME_1)
                    || (role.getRoleName().trim()
                            .equals(GeneralConstant.STOREROLENAME_2))) {
                session.setAttribute("storeflag", Boolean.TRUE);
                session.setAttribute("storeId", operator.getStoreId());
            } else {
                session.setAttribute("storeflag", Boolean.FALSE);
            }
            session.setAttribute("roleName", role.getRoleName());
        } else {
            session.setAttribute("storeflag", Boolean.FALSE);
        }

        return "system.index";
    }

}
