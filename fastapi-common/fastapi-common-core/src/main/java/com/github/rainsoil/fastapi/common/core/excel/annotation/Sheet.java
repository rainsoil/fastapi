package com.github.rainsoil.fastapi.common.core.excel.annotation;

import java.lang.annotation.*;

/**
 * sheet
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sheet {


	/**
	 * sheet的名字
	 *
	 * @return java.lang.String
	 * @since 2023/05/22
	 */
	String name() default "";


	/**
	 * 下标
	 *
	 * @return int
	 * @since 2023/05/22
	 */
	int index() default 0;


	/**
	 * sheet对应的类class
	 *
	 * @return java.lang.Class<?>
	 * @since 2023/05/22
	 */
	Class<?> headClazz();


	/**
	 * 标题的行数
	 *
	 * @return int
	 * @since 2023/05/22
	 */
	int headRowNumber() default 1;

}
