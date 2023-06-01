package com.github.rainsoil.fastapi.common.core.excel.annotation;

import java.lang.annotation.*;

/**
 * excel 字典转换注解
 *
 * @author luyanan
 * @since 2023/05/30
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelDict {


	/**
	 * 字典的type
	 *
	 * @return java.lang.String
	 * @since 2023/05/30
	 */
	String value();
}
