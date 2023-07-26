package com.github.rainsoil.fastapi.core.core;

import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制层
 *
 * @author luyanan
 * @since 2023/06/14
 **/

public class BaseController {

	@Autowired
	private LoginUserService loginUserService;


	/**
	 * 获取当前登录用户id
	 *
	 * @return java.lang.Long
	 * @since 2023/06/14
	 */
	protected Long getUserId() {
		return loginUserService.getUser().getUserId();
	}


	/**
	 * 获取当前登录用户
	 *
	 * @return LoginUser
	 * @since 2023/06/14
	 */
	protected LoginUser getUser() {
		return loginUserService.getUser();
	}
}
