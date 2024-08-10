package com.pj.controller;

import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author click33
 * @since 2024/8/7
 */
@RestController
public class HomeController {

    // 首页       --- http://localhost:8083/
    @RequestMapping("/")
    public Object index(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("isLogin", !(authentication instanceof AnonymousAuthenticationToken));

        return new ModelAndView("index.html");
    }

    // favicon.ico       --- http://localhost:8083/favicon.ico
    @RequestMapping("/favicon.ico")
    public Object favicon() {
        return "favicon.ico";
    }


}