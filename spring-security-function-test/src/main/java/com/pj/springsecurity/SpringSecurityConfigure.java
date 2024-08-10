package com.pj.springsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.util.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SpringSecurity 配置类
 *
 * @since 2024/8/6
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityConfigure {

    // 未登录处理逻辑、权限不足处理逻辑
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * Spring Security的核心过滤器链配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 定义安全请求拦截规则
        httpSecurity.authorizeHttpRequests(router -> {
            router
                    // 放行接口
                    // .requestMatchers("/acc/doLogin", "/acc/isLogin").permitAll()
                    .requestMatchers("/route-check/getInfo1").permitAll()    // 不拦截
                    .requestMatchers("/route-check/getInfo2").authenticated()    // 需要登录
                    .requestMatchers("/route-check/getInfo3").hasRole("admin")    // 需要 admin 角色
                    .requestMatchers("/route-check/getInfo4").hasAuthority("user:add2")    // 需要 user:add2 权限

                    .anyRequest().permitAll(); // 所有请求都放行
                    // .anyRequest().authenticated(); // 所有请求都需要认证
                });

        // 使用默认的表单登录
        httpSecurity.formLogin(withDefaults());

        // 注销相关配置
        httpSecurity.logout(logout -> {
            logout.logoutUrl("/acc/logout");
            logout.logoutSuccessHandler((request, response, authentication) -> {
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                String jsonStr = new ObjectMapper().writeValueAsString(AjaxJson.getSuccess("注销成功!"));
                response.getWriter().write(jsonStr);
            });
        });

        // 异常处理
        httpSecurity.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            // 权限不足处理方案
            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(accessDeniedHandler);
            // 未登录 处理逻辑
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(accessDeniedHandler);
        });

        // 是否启用 csrf 防御
        httpSecurity.csrf( csrf -> csrf.disable() );

        // 一些安全相关的全局响应头
        httpSecurity.headers(httpSecurityHeadersConfigurer -> {
            httpSecurityHeadersConfigurer.cacheControl(HeadersConfigurer.CacheControlConfig::disable);
            httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
        });

        return httpSecurity.build();
    }

    /**
     * Spring Security 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
