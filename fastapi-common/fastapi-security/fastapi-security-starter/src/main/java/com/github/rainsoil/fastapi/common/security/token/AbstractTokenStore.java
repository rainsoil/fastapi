package com.github.rainsoil.fastapi.common.security.token;

import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.token.AccessToken;
import com.github.rainsoil.fastapi.common.security.core.token.TokenStore;

/**
 * token store 抽象类
 *
 * @author luyanan
 * @since 2023/06/06
 **/
public abstract class AbstractTokenStore implements TokenStore {




	@Override
	public AccessToken getAccessToken(LoginUser loginUser) {
		AccessToken accessToken = createAccessToken(loginUser);
		storeAccessToken(loginUser, accessToken);
		return accessToken;
	}

	/**
	 * 创建token
	 *
	 * @param loginUser 登录用户
	 * @return com.github.rainsoil.fastapi.common.security.core.token.AccessToken
	 * @since 2023/06/07
	 */
	protected abstract AccessToken createAccessToken(LoginUser loginUser);

	@Override
	public void removeAccessToken(String token) {
		this.unsupportedOperation();
	}


	/**
	 * 不支持的方法
	 *
	 * @since 2023/06/07
	 */
	protected void unsupportedOperation() {
		throw new UnsupportedOperationException();
	}
}
