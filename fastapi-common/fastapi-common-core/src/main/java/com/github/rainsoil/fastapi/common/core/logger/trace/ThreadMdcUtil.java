package com.github.rainsoil.fastapi.common.core.logger.trace;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 线程链路id
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@UtilityClass
public class ThreadMdcUtil {


	/**
	 * 设置链路id
	 *
	 * @since 2023/05/22
	 */
	public void setTraceIdIfAbsent() {
		if (MDC.get(TraceIdUtil.TRACE_ID) == null) {
			MDC.put(TraceIdUtil.TRACE_ID, TraceIdUtil.generateTraceId());
		}
	}


	/**
	 * 线程转换,链路传递
	 *
	 * @param callable callable
	 * @param context  context
	 * @param <T>      线程对象泛型
	 * @return java.util.concurrent.Callable<T>
	 * @since 2023/05/22
	 */
	public <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
		return () -> {
			if (context == null) {
				MDC.clear();
			} else {
				MDC.setContextMap(context);
			}
			setTraceIdIfAbsent();
			try {
				return callable.call();
			} finally {
				MDC.clear();
			}
		};
	}


	/**
	 * 线程转换,链路传递
	 *
	 * @param runnable runnable
	 * @param context  context
	 * @return java.lang.Runnable
	 * @since 2023/05/22
	 */
	public Runnable wrap(final Runnable runnable, final Map<String, String> context) {
		return () -> {
			if (context == null) {
				MDC.clear();
			} else {
				MDC.setContextMap(context);
			}
			//设置traceId
			setTraceIdIfAbsent();
			try {
				runnable.run();
			} finally {
				MDC.clear();
			}
		};
	}
}
