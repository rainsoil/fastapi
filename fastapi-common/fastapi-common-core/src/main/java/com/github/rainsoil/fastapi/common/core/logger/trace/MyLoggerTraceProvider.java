package com.github.rainsoil.fastapi.common.core.logger.trace;

/**
 * 自定义链路获取
 *
 * @author luyanan
 * @since 2023/05/22
 **/
public class MyLoggerTraceProvider implements LoggerTraceProvider {
	@Override
	public String traceId() {
		return TraceIdUtil.getTraceId();
	}
}
