package com.github.rainsoil.fastapi.common.core.logger.trace;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志链路拦截器
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Slf4j
@Configuration
public class LogTraceInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//如果有上层调用就用上层的ID
		String traceId = request.getHeader(TraceIdUtil.TRACE_ID);
		if (StrUtil.isEmpty(traceId)) {
			TraceIdUtil.setTraceId(TraceIdUtil.generateTraceId());
		} else {
			TraceIdUtil.setTraceId(traceId);
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//调用结束后删除
		TraceIdUtil.remove();
	}

}
