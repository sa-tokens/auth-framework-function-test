package com.pj.controller;

import com.pj.util.AjaxJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 * @author click33
 * @since 2024/8/8
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    // 测试从 session 上存取值  ---- http://localhost:8082/test/testSession
    @RequestMapping("testSession")
    public AjaxJson test() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        System.out.println("从 session 上取值：" + session.getAttribute("name"));
        session.setAttribute("name", "zhang");
        System.out.println("从 session 上取值：" + session.getAttribute("name"));

        return AjaxJson.getSuccess();
    }

}