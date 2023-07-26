package com.github.rainsoil.fastapi.common.security.handler;

import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.LoginUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 登录用户实现
 *
 * @author luyanan
 * @since 2023/06/08
 **/
public class LoginUserServiceImpl implements LoginUserService {
	@Override
	public LoginUser getUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (null == context) {
			return null;
		}
		Authentication authentication = context.getAuthentication();
		if (null == authentication) {
			return null;
		}
		Object details = authentication.getPrincipal();
		if (null == details) {
			return null;
		}
		if (details instanceof LoginUser) {
			return (LoginUser) details;
		} else {
			return null;
		}
	}
}
