package com.github.rainsoil.fastapi.common.core.excel.config;

import com.github.rainsoil.fastapi.common.core.excel.core.RequestMappingHandlerAdapterPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * easy excel自动配置类
 *
 * @author luyanan
 * @since 2023/05/28
 **/
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)

public class EasyExcelAutoConfiguration {
	@Bean
	public RequestMappingHandlerAdapterPostProcessor requestMappingHandlerAdapterPostProcessor() {
		return new RequestMappingHandlerAdapterPostProcessor();
	}
}
