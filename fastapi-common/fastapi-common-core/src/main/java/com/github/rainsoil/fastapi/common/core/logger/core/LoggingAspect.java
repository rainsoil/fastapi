package com.github.rainsoil.fastapi.common.core.logger.core;

import cn.hutool.core.lang.Opt;
import com.github.rainsoil.fastapi.common.core.logger.annotation.IgnoreLogger;
import com.github.rainsoil.fastapi.common.core.logger.trace.LoggerTraceProvider;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 日志拦截器
 *
 * @author luyanan
 * @since 2023/05/21
 **/
@Slf4j
@Aspect
@Order(-1)
public class LoggingAspect {

	@Autowired(required = false)
	private LoggerTraceProvider loggerTraceProvider;

	/**
	 * 对注解进行拦截
	 *
	 * @param proceedingJoinPoint proceedingJoinPoint
	 * @return java.lang.Object
	 * @since 2023/05/21
	 */
	@Around("@annotation(org.springframework.web.bind.annotation.RestController)" +
			"||@annotation(org.springframework.stereotype.Controller)" +
			"||@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.PatchMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.PutMapping)")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object[] args = proceedingJoinPoint.getArgs();
		Throwable e = null;

		Object result = null;

		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable ex) {
			e = ex;
			throw ex;
		} finally {
			long endTime = System.currentTimeMillis();
			MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
			Method method =
					methodSignature.getMethod();
			IgnoreLogger ignoreLogger = method.getAnnotation(IgnoreLogger.class);
			if (isIgnore(ignoreLogger, IgnoreLogger.Type.PARAMS)) {
				args = null;
			}
			if (isIgnore(ignoreLogger, IgnoreLogger.Type.RESULT)) {
				result = null;
			}
			String traceId = Opt.ofNullable(loggerTraceProvider).map(a -> a.traceId()).orElse(null);

			LoggerEventDto loggerEventDto = new LoggerEventDto()
					.setEndTime(System.currentTimeMillis())
					.setStartTime(startTime)
					.setThrowable(e)
					.setTraceId(traceId)
					.setSignature(proceedingJoinPoint.getSignature())
					.setResult(result)
					.setArgs(args);
			// 发送事件
			SpringContextHolder.publishEvent(new LoggerEvent(loggerEventDto));
		}

		return result;

	}

	/**
	 * 是否忽略
	 *
	 * @param ignoreLogger 忽略注解
	 * @param type         忽略类型
	 * @return boolean
	 * @since 2023/05/21
	 */
	private boolean isIgnore(IgnoreLogger ignoreLogger, IgnoreLogger.Type type) {
		return null != ignoreLogger && (ignoreLogger.type().equals(type) ||
				ignoreLogger.type().equals(IgnoreLogger.Type.ALL));
	}
}
