package com.github.rainsoil.fastapi.common.core.excel.config;

import com.github.rainsoil.fastapi.common.core.excel.conver.DictConverter;
import com.github.rainsoil.fastapi.common.core.excel.conver.GlobalConverterRegister;
import com.github.rainsoil.fastapi.common.core.excel.core.RequestMappingHandlerAdapterPostProcessor;
import com.github.rainsoil.fastapi.common.core.excel.support.ExcelDictSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * easy excel自动配置类
 *
 * @author luyanan
 * @since 2023/05/28
 **/
@Configuration
@EnableConfigurationProperties(ExcelProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)

public class EasyExcelAutoConfiguration {
	/**
	 * RequestMappingHandlerAdapterPostProcessor
	 *
	 * @return com.github.rainsoil.fastapi.common.core.excel.core.RequestMappingHandlerAdapterPostProcessor
	 * @since 2023/06/01
	 */
	@Bean
	public RequestMappingHandlerAdapterPostProcessor requestMappingHandlerAdapterPostProcessor() {
		return new RequestMappingHandlerAdapterPostProcessor();
	}

	/**
	 * 全局转换器注册
	 *
	 * @return com.github.rainsoil.fastapi.common.core.excel.conver.GlobalConverterRegister
	 * @since 2023/06/01
	 */
	@Bean
	public GlobalConverterRegister globalConverterRegister() {
		return new GlobalConverterRegister();
	}

	/**
	 * 字典值转换
	 *
	 * @param dictSupport 字典值获取支持类
	 * @return com.github.rainsoil.fastapi.common.core.excel.conver.DictConverter
	 * @since 2023/06/01
	 */
	@ConditionalOnProperty(prefix = ExcelProperties.PREFIX, name = "dict", havingValue = "true", matchIfMissing = false)
	@Bean("dictConverter")
	public DictConverter dictConverter(ExcelDictSupport dictSupport) {
		return new DictConverter(dictSupport);
	}
}
