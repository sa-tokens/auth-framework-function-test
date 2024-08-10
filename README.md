

### auth-framework-function-test 介绍

Java 权限认证框架功能 测试 / 对比 / 迁移。

- Sa-Token `v1.38.0` (SpringBoot v3.3.2)
- Apache Shiro `v1.13.0` (SpringBoot v2.7.18)
- Spring Security `v6.3.1` (SpringBoot v3.3.2)
- JWT `Hutool v5.8.29` (SpringBoot v3.3.2)

因个人精力&能力有限，目前只展示部分常见功能的对比，也欢迎大家一起贡献案例，提交pr。

配套文章：[https://sa-token.cc/doc.html#/fun/auth-framework-function-test](https://sa-token.cc/doc.html#/fun/auth-framework-function-test)


### 对比功能点

| 对比项                           | Sa-Token | Shiro | SpringSecurity | JWT |
|------------------------------    | ------ | ---------- | --------- |--- |
| 依赖引入                         | ✅    | ✅         | ✅       | ✅ |
| 会话登录                         | ✅    | ✅         | ✅       | ✅ |
| 会话状态查询                     | ✅    | ✅         | ✅       | ✅ |
| 会话注销                         | ✅    | ✅         | ✅       | ⚪ |
| 账号密码登录（MD5 加 salt）      | ✅    | ✅         | ✅       | ✅ |
| 从上下文获取当前登录 User 信息   | ✅    | ✅         | ✅       | ✅ |
| 从会话上下文上存取值             | ✅    | ✅         | ✅       | ⚪ |
| 角色认证                         | ✅    | ✅         | ✅       | ⚪ |
| 权限认证                         | ✅    | ✅         | ✅       | ⚪ |
| 注解鉴权                         | ✅    | ✅         | ✅       | ⚪  |
| 路由拦截鉴权                     | ✅    | ✅         | ✅       | ⚪  |
| 鉴权未通过的处理方案             | ✅    | ✅         | ✅       | ✅  |
| 和 Thymeleaf 集成                | ✅    | ✅         | ✅       | ⚪  |
| 前后端分离                       | ✅    | ✅         | ✅       | ✅  |
| 集成 Redis                       | ✅    | ✅         | ✅       | ⚪  |

✅ 已完成 ⚪ 不支持

