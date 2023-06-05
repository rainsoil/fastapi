package com.github.rainsoil.fastapi.common.security.core.token;

import com.github.rainsoil.fastapi.common.security.core.LoginUser;

/**
 * token存储
 *
 * @author luyanan
 * @since 2023/06/05
 **/
public interface TokenStore {


	/**
	 * 根据登录用户创建token
	 *
	 * @param loginUser 用户
	 * @return com.github.rainsoil.fastapi.common.security.core.token.AccessToken
	 * @since 2023/06/05
	 */
	AccessToken getAccessToken(LoginUser loginUser);


	/**
	 * 根据token解析登录用户
	 *
	 * @param token token
	 * @return com.github.rainsoil.fastapi.common.security.core.LoginUser
	 * @since 2023/06/05
	 */
	LoginUser readAccessToken(String token);


	/**
	 * 存储token
	 *
	 * @param loginUser   登录用户
	 * @param accessToken token
	 * @since 2023/06/05
	 */
	void storeAccessToken(LoginUser loginUser, AccessToken accessToken);


	/**
	 * 移除token
	 *
	 * @param token token
	 * @since 2023/06/05
	 */
	void removeAccessToken(String token);
}
