package com.github.rainsoil.fastapi.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置文件
 *
 * @author luyanan
 * @since 2023/06/05
 **/

@Data
@ConfigurationProperties(prefix = SpringSecurityProperties.PREFIX)
public class SpringSecurityProperties {
	public static final String PREFIX = "spring.security";

	/**
	 * 忽略授权登录的url
	 *
	 * @since 2023/06/05
	 */

	private List<String> ignoreUrls = new ArrayList<>();
	/**
	 * token的配置
	 *
	 * @since 2023/06/05
	 */

	private TokenProperties token = new TokenProperties();

	/**
	 * token的配置文件
	 *
	 * @author luyanan
	 * @since 2023/06/05
	 */

	@Data
	public class TokenProperties {


		/**
		 * token类型
		 *
		 * @since 2023/06/05
		 */

		private String tokenType = "Bearer";
		/**
		 * token 的key
		 *
		 * @since 2023/06/05
		 */

		private String headerKey = "Authorization";
		/**
		 * 加密的密钥
		 *
		 * @since 2023/06/05
		 */

		private String signKey;

		/**
		 * 过期时间
		 *
		 * @since 2023/06/05
		 */

		private Long expireTime = 30 * 24 * 60 * 60 * 1000L;
	}
}
