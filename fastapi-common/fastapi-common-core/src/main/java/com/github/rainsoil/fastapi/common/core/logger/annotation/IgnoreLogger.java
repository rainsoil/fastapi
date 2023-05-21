package com.github.rainsoil.fastapi.common.core.logger.annotation;

import org.checkerframework.common.value.qual.IntRange;

import java.lang.annotation.*;

/**
 * 忽略日志拦截注解
 *
 * @author luyanan
 * @since 2023/05/21
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnoreLogger {
	Type type() default Type.ALL;

	enum Type {


		/**
		 * 忽略请求参数
		 *
		 * @author luyanan
		 * @since 2023/05/21
		 */
		PARAMS,


		/**
		 * 忽略返回结果
		 *
		 * @author luyanan
		 * @since 2023/05/21
		 */
		RESULT,

		/**
		 * 全部忽略
		 *
		 * @author luyanan
		 * @since 2023/05/21
		 */
		ALL
	}
}
