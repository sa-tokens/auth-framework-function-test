package com.pj.current;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.pj.util.AjaxJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 
 */
@RestControllerAdvice
public class GlobalException {

	// 全局异常拦截（拦截项目中的所有异常）
	@ExceptionHandler
	public AjaxJson handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {

		// 打印堆栈，以供调试
		System.out.println("全局异常---------------");
		e.printStackTrace();

		// 返回给前端
		return AjaxJson.getError(e.getMessage());
	}

	@ExceptionHandler(NotLoginException.class)
	public AjaxJson handlerException(NotLoginException e) {
		e.printStackTrace();
		return AjaxJson.get(401, "未登录");
	}

	@ExceptionHandler(NotRoleException.class)
	public AjaxJson handlerException(NotRoleException e) {
		e.printStackTrace();
		return AjaxJson.get(403, "缺少角色：" + e.getRole());
	}

	@ExceptionHandler(NotPermissionException.class)
	public AjaxJson handlerException(NotPermissionException e) {
		e.printStackTrace();
		return AjaxJson.get(403, "缺少权限：" + e.getPermission());
	}

}
