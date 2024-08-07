package com.pj.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 * @since 2024/8/6
 */
@Configuration
public class ShiroConfigure {

    // 自定义 Realm
    @Bean
    public MyRealm myRealm() {
        MyRealm realm = new MyRealm();
        // 设定凭证匹配器
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("md5");
//        realm.setCredentialsMatcher(credentialsMatcher);
        // myCredentialsMatcher.setHashIterations(512);  // 散列次数
        // 返回
        return realm;
    }

    // 自定义 securityManager
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());

        // 自定义session管理 使用redis
        manager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager());

        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());

        // 路由拦截鉴权
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/route-check/getInfo1", "anon"); // 不拦截
        filterMap.put("/route-check/getInfo2", "authc"); // 需要登录
        filterMap.put("/route-check/getInfo3", "perms[admin2]"); // 需要角色
        filterMap.put("/route-check/getInfo4", "perms[user:add3]"); // 需要权限
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/401");  // 未登录时跳转的 url
        bean.setUnauthorizedUrl("/403");  // 未授权时跳转的 url

        return bean;
    }

    /**
     * 配置 ShiroDialect（Shiro 方言） 对象
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    // -------- 以下为 shiro redis 相关 --------

    // Shiro redis 连接信息
    @Value("${spring.redis.shiro.host}")
    private String host;
    @Value("${spring.redis.shiro.database}")
    private int database;
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;

    /**
     * 配置shiro redisManager
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        if(StringUtils.hasText(password)){
            redisManager.setPassword(password);
        }
        redisManager.setDatabase(database);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis 实现
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO redis 实现
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    // 自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

}
