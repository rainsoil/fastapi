package com.github.rainsoil.fastapi.common.core.excel.annotation;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.rainsoil.fastapi.common.core.excel.enums.Scene;

import java.lang.annotation.*;

/**
 * excel导出注解
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExcel {


	/**
	 * 导出的文件名
	 *
	 * @return java.lang.String
	 * @since 2023/05/22
	 */
	String name();


	/**
	 * 导出的文件后缀
	 *
	 * @return com.alibaba.excel.support.ExcelTypeEnum
	 * @since 2023/05/22
	 */
	ExcelTypeEnum suffix() default ExcelTypeEnum.XLSX;


	/**
	 * sheet配置
	 *
	 * @return com.github.rainsoil.fastapi.common.core.excel.annotation.Sheet[]
	 * @since 2023/05/22
	 */
	Sheet[] sheets() default {};


	/**
	 * 导出模式
	 *
	 * @return Scene
	 * @since 2023/05/22
	 */
	Scene scene() default Scene.NORMAL;
}
