package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author click33
 * @since 2024/8/7
 */
@Controller
public class HomeController {

    // 首页       --- http://localhost:8081/
    @RequestMapping("/")
    public Object index(HttpServletRequest request) {
        request.setAttribute("isLogin", StpUtil.isLogin());
        return new ModelAndView("index.html");
    }

    // favicon.ico       --- http://localhost:8084/favicon.ico
    @RequestMapping("/favicon.ico")
    public Object favicon() {
        return "favicon.ico";
    }

}