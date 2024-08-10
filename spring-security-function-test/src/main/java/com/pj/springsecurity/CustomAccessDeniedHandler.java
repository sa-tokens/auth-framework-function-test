package com.pj.springsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.util.AjaxJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 自定义 未登录、无权限配置
 *
 * @author click33
 * @since 2024/8/8
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler, AuthenticationEntryPoint, Serializable {

    // 未登录异常
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //验证为未登陆状态会进入此方法，认证错误
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = new ObjectMapper().writeValueAsString(AjaxJson.get(401, "请先进行登录"));
        printWriter.write(body);
        printWriter.flush();
    }

    // 权限不足
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // 登陆状态下，权限不足执行该方法
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = new ObjectMapper().writeValueAsString(AjaxJson.get(403, "权限不足"));
        printWriter.write(body);
        printWriter.flush();
    }

}