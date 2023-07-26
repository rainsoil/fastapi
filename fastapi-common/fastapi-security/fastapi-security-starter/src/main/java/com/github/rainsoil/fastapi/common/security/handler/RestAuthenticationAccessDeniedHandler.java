package com.github.rainsoil.fastapi.common.security.handler;

import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.exception.GlobalMsgCode;
import com.github.rainsoil.fastapi.common.core.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 访问权限不足
 *
 * @author luyanan
 * @since 2023/06/06
 **/
@Slf4j
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

		log.debug("{}访问权限不足:{}", request.getRequestURI(), e.getMessage());
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(JsonUtils.toJson(R.failed(GlobalMsgCode.FORBIDDEN, e.getMessage())));
		printWriter.flush();
	}
}
