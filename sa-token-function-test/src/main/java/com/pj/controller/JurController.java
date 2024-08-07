package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.util.AjaxJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试 
 * @author click33
 *
 */
@RestController
@RequestMapping("/jur/")
public class JurController {

	// 角色判断  ---- http://localhost:8081/jur/assertRole
	@RequestMapping("assertRole")
	public AjaxJson assertRole() {
		// is 模式，返回 true 或 false
		System.out.println("单个权限判断：" + StpUtil.hasRole("admin"));
		System.out.println("多个权限判断(and)：" + StpUtil.hasRoleAnd("admin", "dev-admin"));
		System.out.println("多个权限判断(or)：" + StpUtil.hasRoleOr("admin", "dev-admin"));

		// check 模式，无角色时抛出异常
		StpUtil.checkRole("admin");  // 单个 check
		StpUtil.checkRoleAnd("admin", "dev-admin"); // 多个 check (and)
		StpUtil.checkRoleOr("admin", "dev-admin"); // 多个 check (or)

		return AjaxJson.getSuccess();
	}

	// 权限判断  ---- http://localhost:8081/jur/assertPermission
	@RequestMapping("assertPermission")
	public AjaxJson assertPermission() {
		// is 模式，返回 true 或 false
		System.out.println("单个权限判断：" + StpUtil.hasPermission("user:add"));
		System.out.println("多个权限判断(and)：" + StpUtil.hasPermissionAnd("user:add", "user:delete22"));
		System.out.println("多个权限判断(or)：" + StpUtil.hasPermissionOr("user:add", "user:delete22"));

		// check 模式，无权限时抛出异常
		StpUtil.checkPermission("user:add");  // 单个 check
		StpUtil.checkPermissionAnd("user:add", "user:delete22"); // 多个 check (and)
		StpUtil.checkPermissionOr("user:add", "user:delete22"); // 多个 check (or)

		return AjaxJson.getSuccess();
	}

}
