package com.pj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author click33
 * @since 2024/8/7
 */
@RestController
public class HomeController {

    // favicon.ico       --- http://localhost:8084/favicon.ico
    @RequestMapping("/favicon.ico")
    public Object favicon() {
        return "favicon.ico";
    }

}