package com.pj.controller;

import com.pj.util.AjaxJson;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    // 登录校验   ---- http://localhost:8082/at-check/checkLogin
    @RequiresAuthentication
    @RequestMapping("checkLogin")
    public AjaxJson checkLogin() {
        return AjaxJson.getSuccess();
    }

    // 角色校验   ---- http://localhost:8082/at-check/checkRole
    @RequiresRoles("admin")
    @RequestMapping("checkRole")
    public AjaxJson checkRole() {
        return AjaxJson.getSuccess();
    }

    // 权限校验   ---- http://localhost:8082/at-check/checkPermission
    @RequiresPermissions("user:add")
    @RequestMapping("checkPermission")
    public AjaxJson checkPermission() {
        return AjaxJson.getSuccess();
    }

}
