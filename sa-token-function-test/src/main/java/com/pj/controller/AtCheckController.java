package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import com.pj.util.AjaxJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * shiro 注解鉴权
 *
 * @author click33
 * @since 2022-8-6
 */
@RestController
@RequestMapping("/at-check/")
public class AtCheckController {

    // 登录校验   ---- http://localhost:8081/at-check/checkLogin
    @SaCheckLogin
    @RequestMapping("checkLogin")
    public AjaxJson checkLogin() {
        return AjaxJson.getSuccess();
    }

    // 角色校验   ---- http://localhost:8081/at-check/checkRole
    @SaCheckRole("admin")
    @RequestMapping("checkRole")
    public AjaxJson checkRole() {
        return AjaxJson.getSuccess();
    }

    // 权限校验   ---- http://localhost:8081/at-check/checkPermission
    @SaCheckPermission("user:add")
    @RequestMapping("checkPermission")
    public AjaxJson checkPermission() {
        return AjaxJson.getSuccess();
    }

    // 忽略认证校验   ---- http://localhost:8081/at-check/ignoreCheck
    @SaIgnore
    @SaCheckLogin
    @RequestMapping("ignoreCheck")
    public AjaxJson ignoreCheck() {
        return AjaxJson.getSuccess();
    }

}
