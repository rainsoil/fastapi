package com.github.rainsoil.fastapi.common.security.core.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 刷新token
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Data
@Accessors(chain = true)
public class AccessToken {


	/**
	 * 刷新token
	 *
	 * @since 2023/06/04
	 */
	@JsonProperty("access_token")
	private String accessToken;


	/**
	 * 有效期
	 *
	 * @since 2023/06/04
	 */

	@JsonProperty("expires_in")
	private Long expiresIn;


	/**
	 * 客户端id
	 *
	 * @since 2023/06/04
	 */

	@JsonProperty("client_id")
	private String clientId;


	/**
	 * token类型
	 *
	 * @since 2023/06/04
	 */

	@JsonProperty("token_type")
	private String tokenType = "bearer";

	private String scope = "server";
}
