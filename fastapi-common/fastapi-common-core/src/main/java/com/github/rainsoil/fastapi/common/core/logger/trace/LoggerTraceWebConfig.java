package com.github.rainsoil.fastapi.common.core.logger.trace;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志拦截器配置
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@RequiredArgsConstructor
public class LoggerTraceWebConfig implements WebMvcConfigurer {


	private final LogTraceInterceptor logTraceInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logTraceInterceptor).addPathPatterns("/**");
	}
}
