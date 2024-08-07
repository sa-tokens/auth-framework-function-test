package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.mockdao.SysUser;
import com.pj.mockdao.SysUserDao;
import com.pj.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	SysUserDao sysUserDao;

	// 测试登录  ---- http://localhost:8081/acc/doLogin?username=zhang&password=123456
	@RequestMapping("doLogin")
	public AjaxJson doLogin(String username, String password) {
		// 校验
		SysUser user = sysUserDao.findByUsername(username);
		if(user == null) {
			return AjaxJson.getError("用户不存在");
		}
		if(!user.getPassword().equals(password)) {
			return AjaxJson.getError("密码错误");
		}
		// 登录
		StpUtil.login(user.getId());
		StpUtil.getSession().set("user", user);
		return AjaxJson.getSuccess("登录成功").set("satoken", StpUtil.getTokenValue());
	}

	// 查询登录状态  ---- http://localhost:8081/acc/isLogin
	@RequestMapping("isLogin")
	public AjaxJson isLogin() {
		if(StpUtil.isLogin()) {
			return AjaxJson.getSuccess("已登录，账号id：" + StpUtil.getLoginId());
		}
		return AjaxJson.getError("未登录");
	}

	// 测试注销  ---- http://localhost:8081/acc/logout
	@RequestMapping("logout")
	public AjaxJson logout() {
		StpUtil.logout();
		return AjaxJson.getSuccess("注销成功");
	}

	// 从上下文获取当前登录 User 信息  ---- http://localhost:8081/acc/getCurrUser
	@RequestMapping("getCurrUser")
	public AjaxJson getCurrUser() {
		return AjaxJson.getSuccess()
				.set("id", StpUtil.getLoginId())
				.set("user", StpUtil.getSession().get("user"));
	}

}
