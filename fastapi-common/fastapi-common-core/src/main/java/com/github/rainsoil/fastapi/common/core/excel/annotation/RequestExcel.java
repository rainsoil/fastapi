package com.github.rainsoil.fastapi.common.core.excel.annotation;

import java.lang.annotation.*;

/**
 * 请求的excel
 *
 * @author luyanan
 * @since 2023/05/22
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestExcel {

	/**
	 * sheet配置
	 *
	 * @author luyanan
	 * @since 2023/05/22
	 */
	Sheet[] sheets();

}
