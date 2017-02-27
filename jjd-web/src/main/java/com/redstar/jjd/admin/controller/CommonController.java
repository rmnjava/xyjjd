package com.redstar.jjd.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
public class CommonController {
    // @Autowired
    // private OperatorService operatorService;
    //
    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // public String login() {
    // return "system.login";
    // }
    //
    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public String login(OperatorView user, Model model,
    // HttpServletRequest request) {
    // Operator u = operatorService.login(user);
    // request.getSession().setAttribute("loginUser", u);
    // model.addAttribute("loginUser", u);
    // return "user.list";
    // }
    //
    // @RequestMapping("/logout")
    // public String logout(Model model, HttpSession session) {
    // model.asMap().remove("loginUser");
    // session.invalidate();
    // return "redirect:/login";
    // }
}
