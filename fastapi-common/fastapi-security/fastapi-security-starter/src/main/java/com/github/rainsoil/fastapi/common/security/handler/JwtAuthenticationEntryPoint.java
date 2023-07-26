package com.github.rainsoil.fastapi.common.security.handler;

import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.exception.GlobalMsgCode;
import com.github.rainsoil.fastapi.common.core.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 授权失败认证
 *
 * @author luyanan
 * @since 2023/06/06
 **/
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

		log.debug("认证失败:{}", e.getMessage());
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(JsonUtils.toJson(R.failed(GlobalMsgCode.UNAUTHORIZED, e.getMessage())));
		printWriter.flush();
	}
}
