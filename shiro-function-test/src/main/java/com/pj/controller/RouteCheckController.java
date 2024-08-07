package com.pj.controller;

import com.pj.util.AjaxJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * shiro 路由鉴权
 *
 * @author click33
 * @since 2022-8-6
 */
@RestController
@RequestMapping("/route-check/")
public class RouteCheckController {

    // 路由测试1   ---- http://localhost:8082/route-check/getInfo1
    @RequestMapping("getInfo1")
    public AjaxJson getInfo1() {
        return AjaxJson.getSuccess();
    }

    // 路由测试2   ---- http://localhost:8082/route-check/getInfo2
    @RequestMapping("getInfo2")
    public AjaxJson getInfo2() {
        return AjaxJson.getSuccess();
    }

    // 路由测试3   ---- http://localhost:8082/route-check/getInfo3
    @RequestMapping("getInfo3")
    public AjaxJson getInfo3() {
        return AjaxJson.getSuccess();
    }

    // 路由测试4   ---- http://localhost:8082/route-check/getInfo4
    @RequestMapping("getInfo4")
    public AjaxJson getInfo4() {
        return AjaxJson.getSuccess();
    }

}
