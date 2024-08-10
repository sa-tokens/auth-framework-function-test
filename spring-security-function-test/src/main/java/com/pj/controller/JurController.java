package com.pj.controller;

import com.pj.util.AjaxJson;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限测试
 * @author click33
 *
 */
@RestController
@RequestMapping("/jur/")
public class JurController {

	// 角色判断  ---- http://localhost:8083/jur/assertRole
	@RequestMapping("assertRole")
	public AjaxJson assertRole() {
		SecurityExpressionRoot securityExpressionRoot = new SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication()) {};

		System.out.println("单个角色判断：" + securityExpressionRoot.hasRole("admin"));
		System.out.println("多个角色判断(and)：" + (securityExpressionRoot.hasRole("admin") && securityExpressionRoot.hasRole("dev-admin")));
		System.out.println("多个角色判断(or)：" + securityExpressionRoot.hasAnyRole("admin", "dev-admin"));

		return AjaxJson.getSuccess();
	}

	// 权限判断  ---- http://localhost:8083/jur/assertPermission
	@RequestMapping("assertPermission")
	public AjaxJson assertPermission() {
		SecurityExpressionRoot securityExpressionRoot = new SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication()) {};

		System.out.println("单个权限判断：" + securityExpressionRoot.hasAuthority("user:add"));
		System.out.println("多个权限判断(and)：" + (securityExpressionRoot.hasAuthority("user:add") && securityExpressionRoot.hasAuthority("user:delete2")));
		System.out.println("多个权限判断(or)：" + securityExpressionRoot.hasAnyAuthority("user:add", "user:delete2"));

		return AjaxJson.getSuccess();
	}

}