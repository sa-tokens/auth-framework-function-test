package com.pj.satoken;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

/**
 * Thymeleaf 配置类
 *
 * @author click33
 * @since 2024/8/8
 */
@Configuration
public class ThymeleafConfigure {

    // 为 Thymeleaf 注入全局变量，以便在页面中调用 Sa-Token 的方法
    @Autowired
    public void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        viewResolver.addStaticVariable("stp", StpUtil.stpLogic);
    }

}