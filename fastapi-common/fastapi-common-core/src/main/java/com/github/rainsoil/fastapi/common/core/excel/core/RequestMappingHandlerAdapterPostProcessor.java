package com.github.rainsoil.fastapi.common.core.excel.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 请求处理类
 *
 * @author luyanan
 * @since 2023/05/28
 **/
public class RequestMappingHandlerAdapterPostProcessor implements BeanPostProcessor, PriorityOrdered, ResourceLoaderAware {
	private ResourceLoader resourceLoader;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!supports(bean)) {
			return bean;
		}
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = (RequestMappingHandlerAdapter) bean;
		List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
		List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();

		Assert.notEmpty(argumentResolvers,
				"RequestMappingHandlerAdapter's argument resolver is empty, this is illegal state");
		Assert.notEmpty(returnValueHandlers,
				"RequestMappingHandlerAdapter's return-value handler is empty, this is illegal state");

		List<HandlerMethodArgumentResolver> copyArgumentResolvers = new ArrayList<>(argumentResolvers);
		RequestResponseExcelMethodProcessor argumentResolver4RequestExcel = new RequestResponseExcelMethodProcessor(null);
		copyArgumentResolvers.add(0, argumentResolver4RequestExcel);
		requestMappingHandlerAdapter.setArgumentResolvers(Collections.unmodifiableList(copyArgumentResolvers));

		List<HandlerMethodReturnValueHandler> copyReturnValueHandlers = new ArrayList<>(returnValueHandlers);
		RequestResponseExcelMethodProcessor returnValueHandler4ResponseExcel = new RequestResponseExcelMethodProcessor(resourceLoader);
		copyReturnValueHandlers.add(0, returnValueHandler4ResponseExcel);
		requestMappingHandlerAdapter.setReturnValueHandlers(Collections.unmodifiableList(copyReturnValueHandlers));

		return requestMappingHandlerAdapter;

	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}


	/**
	 * 是否支持
	 *
	 * @param bean 类
	 * @return boolean
	 * @since 2023/06/01
	 */
	private boolean supports(Object bean) {
		return bean instanceof RequestMappingHandlerAdapter;
	}
}
