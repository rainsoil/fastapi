package com.github.rainsoil.fastapi.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author luyanan
 * @since 2023/4/4
 **/
@MapperScan("com.github.rainsoil.fastapi.system.mapper")
@SpringBootApplication(scanBasePackages = {
		"com.github.rainsoil.fastapi.core",
		"com.github.rainsoil.fastapi.system",
		"com.github.rainsoil.fastapi.web"
})
public class AppApplication {


	/**
	 * 著启动类
	 *
	 * @param args 参数
	 * @since 2023/4/4
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
