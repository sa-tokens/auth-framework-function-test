package com.pj.controller;

import com.pj.mockdao.SysUser;
import com.pj.util.AjaxJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试 
 * @author click33
 *
 */
@RestController
@RequestMapping("/acc/")
public class LoginController {

	// 测试登录  ---- http://localhost:8082/acc/doLogin?username=zhang&password=123456
	@RequestMapping("doLogin")
	public AjaxJson doLogin(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username, password));
			String token = subject.getSession().getId().toString();
			return AjaxJson.getSuccess("登录成功!").set("token", token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return AjaxJson.getError(e.getMessage());
		}
	}

	// 查询登录状态  ---- http://localhost:8082/acc/isLogin
	@RequestMapping("isLogin")
	public AjaxJson isLogin() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			SysUser sysUser = (SysUser)subject.getPrincipal();
			return AjaxJson.getSuccess("已登录，账号id：" + sysUser.getId());
		}
		return AjaxJson.getError("未登录");
	}

	// 测试注销  ---- http://localhost:8082/acc/logout
	@RequestMapping("logout")
	public AjaxJson logout() {
		SecurityUtils.getSubject().logout();
		return AjaxJson.getSuccess("注销成功");
	}

	// 从上下文获取当前登录 User 信息  ---- http://localhost:8082/acc/getCurrUser
	@RequestMapping("getCurrUser")
	public AjaxJson getCurrUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = (SysUser)subject.getPrincipal();
		return AjaxJson.getSuccess()
				.set("id", sysUser.getId())
				.set("user", sysUser);
	}

}
