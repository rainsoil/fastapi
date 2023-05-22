package com.github.rainsoil.fastapi.common.core.logger.trace;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 链路id 工具类
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@UtilityClass
public class TraceIdUtil {
	public static final String TRACE_ID = "TRACE_ID";


	/**
	 * 获取链路id
	 *
	 * @return java.lang.String
	 * @since 2023/05/22
	 */
	public String getTraceId() {
		String traceId = (String) MDC.get(TRACE_ID);
		return traceId == null ? "" : traceId;
	}

	/**
	 * 设置链路id
	 *
	 * @param traceId 链路id
	 * @since 2023/05/22
	 */
	public void setTraceId(String traceId) {
		MDC.put(TRACE_ID, traceId);
	}


	/**
	 * 移除链路id
	 *
	 */
	public void remove() {
		MDC.remove(TRACE_ID);
	}

	/**
	 * 生成链路id
	 *
	 * @return java.lang.String
	 * @since 2023/05/22
	 */
	public String generateTraceId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
