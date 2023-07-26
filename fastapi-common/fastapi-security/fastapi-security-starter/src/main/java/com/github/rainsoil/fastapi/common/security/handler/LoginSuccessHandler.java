package com.github.rainsoil.fastapi.common.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import com.github.rainsoil.fastapi.common.core.json.JsonUtils;
import com.github.rainsoil.fastapi.common.core.logger.core.LoggerEvent;
import com.github.rainsoil.fastapi.common.core.logger.core.LoggerEventDto;
import com.github.rainsoil.fastapi.common.core.logger.trace.TraceIdUtil;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.token.AccessToken;
import com.github.rainsoil.fastapi.common.security.core.token.TokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 *
 * @author luyanan
 * @since 2023/06/06
 **/
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private TokenStore tokenManage;

	/**
	 * Called when a user has been successfully authenticated.
	 *
	 * @param request        the request which caused the successful authentication
	 * @param response       the response
	 * @param authentication the <tt>Authentication</tt> object which was created during
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Object principal = authentication.getPrincipal();

		LoginUser loginUser = (LoginUser) principal;
		AccessToken token = tokenManage.getAccessToken(loginUser);
		log.debug("{}登录成功,生成的token:{}", loginUser.getUsername(), token.getAccessToken());
		// 发送登录日志请求
		publish(new LoggerEventDto()
				.setType("login")
				.setArgs(new Object[]{loginUser.getUsername()})
				.setResult(token)
				.setStartTime(System.currentTimeMillis())
				.setEndTime(System.currentTimeMillis())
		);
		ServletUtil.write(response, JsonUtils.toJson(token), "application/json;charset=utf-8");
	}


	/**
	 * 日志消息发布
	 *
	 * @param loggerEventDto 日志事件
	 * @since 2023/06/06
	 */
	public void publish(LoggerEventDto loggerEventDto) {


		loggerEventDto.setTraceId(TraceIdUtil.getTraceId());
		// 发送事件
		SpringContextHolder.publishEvent(new LoggerEvent(loggerEventDto));
	}

}
