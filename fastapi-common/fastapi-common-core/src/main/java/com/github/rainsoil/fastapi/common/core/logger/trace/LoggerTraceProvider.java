package com.github.rainsoil.fastapi.common.core.logger.trace;

/**
 * 日志链路提供者
 *
 * @author luyanan
 * @since 2023/05/21
 **/
public interface LoggerTraceProvider {


	/**
	 * 链路id
	 *
	 * @return java.lang.String
	 * @since 2023/05/21
	 */
	String traceId();
}
