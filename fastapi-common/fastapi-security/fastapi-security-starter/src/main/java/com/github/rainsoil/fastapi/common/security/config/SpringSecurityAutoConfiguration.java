package com.github.rainsoil.fastapi.common.security.config;

import com.github.rainsoil.fastapi.common.security.core.LoginUserService;
import com.github.rainsoil.fastapi.common.security.core.token.TokenStore;
import com.github.rainsoil.fastapi.common.security.handler.*;
import com.github.rainsoil.fastapi.common.security.token.SimpleJwtTokenStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 安全自动配置类
 *
 * @author luyanan
 * @since 2023/06/07
 **/
@EnableConfigurationProperties(SpringSecurityProperties.class)
@Configuration
@Import(WebSecurityConfig.class)
public class SpringSecurityAutoConfiguration {


	/**
	 * token存储类
	 *
	 * @param securityProperties 配置文件
	 * @return com.github.rainsoil.fastapi.common.security.core.token.TokenStore
	 * @since 2023/06/07
	 */
	@Bean
	@ConditionalOnMissingBean
	public TokenStore tokenStore(SpringSecurityProperties securityProperties) {
		return new SimpleJwtTokenStore(securityProperties);
	}

	/**
	 * 认证失败
	 *
	 * @return com.github.rainsoil.fastapi.common.security.handler.JwtAuthenticationEntryPoint
	 * @since 2023/06/07
	 */
	@Bean
	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint();
	}

	/**
	 * 权限不足
	 *
	 * @return com.github.rainsoil.fastapi.common.security.handler.RestAuthenticationAccessDeniedHandler
	 * @since 2023/06/07
	 */
	@Bean("RestAuthenticationAccessDeniedHandler")
	public RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler() {
		return new RestAuthenticationAccessDeniedHandler();
	}


	/**
	 * token过滤器
	 *
	 * @param securityProperties 配置文件
	 * @param tokenStore         token store
	 * @return com.github.rainsoil.fastapi.common.security.config.JwtAuthenticationTokenFilter
	 * @since 2023/06/08
	 */
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(SpringSecurityProperties securityProperties, TokenStore tokenStore) {
		return new JwtAuthenticationTokenFilter(securityProperties, tokenStore);
	}


	/**
	 * 登录成功处理器
	 *
	 * @return com.github.rainsoil.fastapi.common.security.handler.LoginSuccessHandler
	 * @since 2023/06/08
	 */
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}


	/**
	 * 登录失败处理器
	 *
	 * @return com.github.rainsoil.fastapi.common.security.handler.LoginFailureHandler
	 * @since 2023/06/08
	 */
	@Bean
	public LoginFailureHandler loginFailureHandler() {
		return new LoginFailureHandler();
	}


	/**
	 * 登录用户实现
	 *
	 * @return LoginUserService
	 * @since 2023/06/08
	 */
	@Bean
	public LoginUserService loginUserService() {
		return new LoginUserServiceImpl();
	}
}
