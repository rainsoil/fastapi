package com.github.rainsoil.fastapi.common.security.core;

import java.util.Optional;

/**
 * 登录用户service
 *
 * @author luyanan
 * @since 2023/06/05
 **/
public interface LoginUserService {


	/**
	 * 获取登录的用户
	 *
	 * @return com.github.rainsoil.fastapi.common.security.core.LoginUser
	 * @since 2023/06/05
	 */
	LoginUser getUser();


	/**
	 * 获取用户id
	 *
	 * @return java.lang.Long
	 * @since 2023/06/05
	 */
	default Long getUserId() {
		return Optional.ofNullable(getUser()).map(a -> a.getUserId()).orElse(null);
	}


	/**
	 * 获取用户名
	 *
	 * @return java.lang.String
	 * @since 2023/06/05
	 */
	default String getUsername() {
		return Optional.ofNullable(getUser()).map(a -> a.getUsername()).orElse(null);
	}
}

