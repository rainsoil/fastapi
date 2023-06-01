package com.github.rainsoil.fastapi.common.core.excel.annotation;

import com.github.rainsoil.fastapi.common.core.excel.config.EasyExcelAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启excel
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EasyExcelAutoConfiguration.class)
public @interface EnableEasyExcel {
}
