package com.github.rainsoil.fastapi.common.security.config;

import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.token.TokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 授权过滤器
 *
 * @author luyanan
 * @since 2023/06/05
 **/
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private final SpringSecurityProperties springSecurityProperties;

	private final TokenStore tokenStore;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		SpringSecurityProperties.TokenProperties tokenProperties = springSecurityProperties.getToken();
		String headerKey = tokenProperties.getHeaderKey();
		String token = request.getHeader(headerKey);
		if (!StringUtils.hasText(token)) {
			// 从cookie中获取
			Cookie[] cookies =
					request.getCookies();
			if (null != cookies && cookies.length > 0 ){
				Cookie cookie = Arrays.stream(cookies).filter(a -> a.getName().equals(headerKey)).findFirst().orElse(null);
				token = Optional.ofNullable(cookie).map(a -> a.getValue()).orElse(null);
			}

		}

		if (!StringUtils.hasText(token)) {
			token = request.getParameter(headerKey);
		}

		if (StringUtils.hasText(token)) {

			try {
				LoginUser loginUser = tokenStore.readAccessToken(token);
				if (loginUser != null && null == SecurityContextHolder.getContext().getAuthentication()) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception e) {
				throw e;
			}
		}
		filterChain.doFilter(request, response);

	}
}
