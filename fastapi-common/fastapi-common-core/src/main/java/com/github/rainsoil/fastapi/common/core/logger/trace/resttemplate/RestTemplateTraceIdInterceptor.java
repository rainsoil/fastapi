package com.github.rainsoil.fastapi.common.core.logger.trace.resttemplate;

import com.github.rainsoil.fastapi.common.core.logger.trace.TraceIdUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.UUID;

/**
 * rest拦截器
 *
 * @author luyanan
 * @since 2023/05/22
 **/
public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
										ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
		String traceId = TraceIdUtil.getTraceId();

		if (traceId != null) {
			request.getHeaders().set(TraceIdUtil.TRACE_ID, traceId);
		} else {
			request.getHeaders().set(TraceIdUtil.TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
		}
		return clientHttpRequestExecution.execute(request, body);
	}
}
