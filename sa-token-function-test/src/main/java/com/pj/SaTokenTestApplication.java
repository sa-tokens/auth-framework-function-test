package com.pj;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Sa-Token 测试  
 * @author click33
 *
 */
@SpringBootApplication
public class SaTokenTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaTokenTestApplication.class, args);
		System.out.println("\n 启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}

}
