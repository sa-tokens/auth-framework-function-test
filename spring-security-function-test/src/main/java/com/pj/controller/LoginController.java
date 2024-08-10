package com.pj.controller;

import com.pj.mockdao.SysUser;
import com.pj.mockdao.SysUserDao;
import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
	AuthenticationManager authenticationManager;

	@Autowired
	SysUserDao sysUserDao;

	// 测试登录  ---- http://localhost:8083/acc/doLogin?username=zhang&password=123456
	@RequestMapping("doLogin")
	public AjaxJson doLogin(String username, String password, HttpServletRequest request) {
		try {
			// 验证账号密码
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			usernamePasswordAuthenticationToken.setDetails(sysUserDao.findByUsername(username));
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			// 存入上下文
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
			// 返回
			String token = request.getSession().getId();
			return AjaxJson.getSuccess("登录成功!").set("token", token);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxJson.getError(e.getMessage());
		}
	}

	// 查询登录状态  ---- http://localhost:8083/acc/isLogin
	@RequestMapping("isLogin")
	public AjaxJson isLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return AjaxJson.getSuccess("是否登录：" + !(authentication instanceof AnonymousAuthenticationToken))
				.set("principal", authentication.getPrincipal())
				.set("details", authentication.getDetails());
	}

	// 测试注销  ---- http://localhost:8083/acc/logout
	@RequestMapping("logout")
	public AjaxJson logout() {
		return AjaxJson.getSuccess("注销成功");
	}

	// 从上下文获取当前登录 User 信息  ---- http://localhost:8083/acc/getCurrUser
	@RequestMapping("getCurrUser")
	public AjaxJson getCurrUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			SysUser sysUser = (SysUser)authentication.getDetails();
			return AjaxJson.getSuccess()
							.set("id", sysUser.getId())
							.set("user", sysUser);
		}
		return AjaxJson.getError("未登录");
	}

}
