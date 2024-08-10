package com.pj.controller;

import cn.hutool.jwt.JWT;
import com.pj.jwt.JwtUtil;
import com.pj.mockdao.SysUser;
import com.pj.mockdao.SysUserDao;
import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
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

	// 测试登录  ---- http://localhost:8084/acc/doLogin?username=zhang&password=123456
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
		String token = JwtUtil.createToken(user.getId(), user, 60 * 60 * 2);
		return AjaxJson.getSuccess("登录成功").set("token", token);
	}

	// 查询登录状态  ---- http://localhost:8084/acc/isLogin
	@RequestMapping("isLogin")
	public AjaxJson isLogin(HttpServletRequest request) {
		try{
			String token = request.getHeader("token");
			JWT jwt = JwtUtil.parseToken(token);
			return AjaxJson.getSuccess("已登录")
					.set("id", jwt.getPayload("userId"))
					.set("user", jwt.getPayload("user"));
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxJson.getError("未登录");
		}
	}

	// 从上下文获取当前登录 User 信息  ---- http://localhost:8084/acc/getCurrUser
	@RequestMapping("getCurrUser")
	public AjaxJson getCurrUser(HttpServletRequest request) {
		try{
			String token = request.getHeader("token");
			JWT jwt = JwtUtil.parseToken(token);
			SysUser sysUser = jwt.getPayloads().get("user", SysUser.class);
			return AjaxJson.getSuccessData(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxJson.getError("未登录");
		}
	}

}
