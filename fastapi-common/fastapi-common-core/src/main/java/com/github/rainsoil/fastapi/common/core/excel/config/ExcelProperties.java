package com.github.rainsoil.fastapi.common.core.excel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * excel的配置文件
 *
 * @author luyanan
 * @since 2023/06/01
 **/
@Data
@ConfigurationProperties(prefix = ExcelProperties.PREFIX)
public class ExcelProperties {
	public  static final  String PREFIX = "excel";

	/**
	 * 字典值处理
	 *
	 * @since 2023/06/01
	 */

	private Boolean dict;

}
