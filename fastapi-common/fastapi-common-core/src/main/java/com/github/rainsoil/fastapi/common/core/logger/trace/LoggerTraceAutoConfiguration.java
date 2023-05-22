package com.github.rainsoil.fastapi.common.core.logger.trace;

import com.github.rainsoil.fastapi.common.core.logger.config.LoggerProperties;
import com.github.rainsoil.fastapi.common.core.logger.trace.resttemplate.RestTemplateTraceIdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * 日志链路自动化配置
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = LoggerProperties.PREFIX, value = "trace", havingValue = "true", matchIfMissing = false)
@Import({LogTraceInterceptor.class, LoggerTraceWebConfig.class})
public class LoggerTraceAutoConfiguration {


	/**
	 * RestTemplate 拦截器
	 *
	 * @return com.github.rainsoil.fastapi.common.core.logger.trace.resttemplate.RestTemplateTraceIdInterceptor
	 * @since 2023/05/22
	 */
	@Bean
	@ConditionalOnBean(RestTemplate.class)
	public RestTemplateTraceIdInterceptor restTemplateTraceIdInterceptor() {
		return new RestTemplateTraceIdInterceptor();
	}

	/**
	 * 链路id提供者
	 *
	 * @return com.github.rainsoil.fastapi.common.core.logger.trace.LoggerTraceProvider
	 * @since 2023/05/22
	 */
	@Bean
	public LoggerTraceProvider loggerTraceProvider() {
		return new MyLoggerTraceProvider();
	}

}
