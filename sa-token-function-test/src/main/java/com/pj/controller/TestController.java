package com.pj.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.pj.util.AjaxJson;
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

    // 测试从 session 上存取值  ---- http://localhost:8081/test/testSession
    @RequestMapping("testSession")
    public AjaxJson test() {
        SaSession session = StpUtil.getSession();

        System.out.println("从 session 上取值：" + session.get("name"));
        session.set("name", "zhang");
        System.out.println("从 session 上取值：" + session.get("name"));

        return AjaxJson.getSuccess();
    }

}