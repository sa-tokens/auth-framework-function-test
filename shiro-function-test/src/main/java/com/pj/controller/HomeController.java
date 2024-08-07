package com.pj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author click33
 * @since 2024/8/7
 */
@Controller
public class HomeController {

    // 首页       --- http://localhost:8082/
    @RequestMapping("/")
    public Object index(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        request.setAttribute("isLogin", subject.isAuthenticated());
        return new ModelAndView("index.html");
    }

}