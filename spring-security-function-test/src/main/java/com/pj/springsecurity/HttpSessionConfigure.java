package com.pj.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * HttpSession 配置
 *
 * @author click33
 * @since 2024/8/11
 */
@Configuration
public class HttpSessionConfigure {

    // HttpSession 读取策略，从 header 头读取 token 参数作为 session id
    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return new HeaderHttpSessionIdResolver("token");
    }

    // session 存储到 redis 的序列化方式
    //    @Bean("springSessionDefaultRedisSerializer")
    //    public RedisSerializer<Object> redisSerializer() {
    ////        return new GenericToStringSerializer<>(Object.class);
    //        return new GenericJackson2JsonRedisSerializer();
    //    }

}
