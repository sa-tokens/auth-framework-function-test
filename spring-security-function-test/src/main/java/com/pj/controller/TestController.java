package com.pj.controller;

import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    // 测试   ---- http://localhost:8083/test/test
    @RequestMapping("test")
    public AjaxJson test(HttpServletRequest request) {
        return AjaxJson.getSuccess();
    }

    // 测试从从会话上下文存取值  ---- http://localhost:8083/test/testSession
    @RequestMapping("testSession")
    public AjaxJson testSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        System.out.println("从 session 上取值：" + session.getAttribute("name"));
        session.setAttribute("name", "zhang");
        System.out.println("从 session 上取值：" + session.getAttribute("name"));
        return AjaxJson.getSuccess();
    }


}