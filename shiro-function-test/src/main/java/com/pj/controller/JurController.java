package com.pj.controller;

import com.pj.util.AjaxJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 权限测试
 * @author click33
 *
 */
@RestController
@RequestMapping("/jur/")
public class JurController {

	// 角色判断  ---- http://localhost:8082/jur/assertRole
	@RequestMapping("assertRole")
	public AjaxJson assertRole() {
		Subject subject = SecurityUtils.getSubject();

		// is 模式，返回 true 或 false
		System.out.println("单个角色判断：" + subject.hasRole("admin"));
		System.out.println("多个角色判断(and)：" + subject.hasAllRoles(Arrays.asList("admin", "dev-admin")));
		System.out.println("多个角色判断(or)：" + (subject.hasRole("admin") || subject.hasRole("dev-admin")));

		// check 模式，无角色时抛出异常
		subject.checkRole("admin");  // 单个 check
		subject.checkRoles("admin", "dev-admin"); // 多个 check (and)

		return AjaxJson.getSuccess();
	}

	// 权限判断  ---- http://localhost:8082/jur/assertPermission
	@RequestMapping("assertPermission")
	public AjaxJson assertPermission() {
		Subject subject = SecurityUtils.getSubject();

		// is 模式，返回 true 或 false
		System.out.println("单个权限判断：" + subject.isPermitted("user:add"));
		System.out.println("多个权限判断(and)：" + subject.isPermittedAll("user:add", "user:delete22"));
		System.out.println("多个权限判断(or)：" + (subject.isPermitted("user:add") || subject.isPermitted("user:delete22")));

		// check 模式，无权限时抛出异常
		subject.checkPermission("user:add");  // 单个 check
		subject.checkPermissions("user:add", "user:delete22"); // 多个 check (and)

		return AjaxJson.getSuccess();
	}

}
