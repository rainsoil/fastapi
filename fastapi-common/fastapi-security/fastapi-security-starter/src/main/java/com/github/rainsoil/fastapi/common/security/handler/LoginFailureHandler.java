package com.github.rainsoil.fastapi.common.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败
 *
 * @author luyanan
 * @since 2023/06/06
 **/
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		log.debug("登录失败了");
		ServletUtil.write(response, JsonUtils.toJson(R.failed("用户名或密码错误")), "application/json;charset=utf-8");

	}
}
