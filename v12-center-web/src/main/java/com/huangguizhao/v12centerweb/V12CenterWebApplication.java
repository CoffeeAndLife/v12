package com.huangguizhao.v12centerweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V12CenterWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12CenterWebApplication.class, args);
	}

}
