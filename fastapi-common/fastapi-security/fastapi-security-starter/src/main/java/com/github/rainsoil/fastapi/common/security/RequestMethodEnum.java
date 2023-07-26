package com.github.rainsoil.fastapi.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 请求枚举
 *
 * @since 2023/06/07
 */

@Getter
@AllArgsConstructor
public enum RequestMethodEnum {

	/**
	 * 搜寻 @AnonymousGetMapping
	 */
	GET("GET"),

	/**
	 * 搜寻 @AnonymousPostMapping
	 */
	POST("POST"),

	/**
	 * 搜寻 @AnonymousPutMapping
	 */
	PUT("PUT"),

	/**
	 * 搜寻 @AnonymousPatchMapping
	 */
	PATCH("PATCH"),

	/**
	 * 搜寻 @AnonymousDeleteMapping
	 */
	DELETE("DELETE"),

	/**
	 * 否则就是所有 Request 接口都放行
	 */
	ALL("All");

	/**
	 * Request 类型
	 */
	private final String type;

	/**
	 * 根据类型查询
	 *
	 * @param type 类型
	 * @return cn.com.genhao2.fastapi.common.security.RequestMethodEnum
	 * @since 2022/12/30/030
	 */
	public static RequestMethodEnum find(String type) {
		for (RequestMethodEnum value : RequestMethodEnum.values()) {
			if (value.getType().equals(type)) {
				return value;
			}
		}
		return ALL;
	}
}

