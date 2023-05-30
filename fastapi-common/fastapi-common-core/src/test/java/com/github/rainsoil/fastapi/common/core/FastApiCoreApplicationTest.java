package com.github.rainsoil.fastapi.common.core;

import com.github.rainsoil.fastapi.common.core.excel.annotation.EnableEasyExcel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 启动测试类
 *
 * @author luyanan
 * @since 2023/3/28/028
 */
@EnableEasyExcel
@SpringBootApplication
public class FastApiCoreApplicationTest {
	public static void main(String[] args) {

		SpringApplication.run(FastApiCoreApplicationTest.class, args);
	}
}
