package com.github.rainsoil.fastapi.common.security.token;

import cn.hutool.core.util.StrUtil;
import com.github.rainsoil.fastapi.common.core.json.JsonUtils;
import com.github.rainsoil.fastapi.common.security.config.SpringSecurityProperties;
import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.common.security.core.token.AccessToken;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * jwt token处理类
 *
 * @author luyanan
 * @since 2023/06/07
 **/
@RequiredArgsConstructor
public class SimpleJwtTokenStore extends AbstractTokenStore {

	private final SpringSecurityProperties securityProperties;


	@Override
	public LoginUser readAccessToken(String token) {
		SpringSecurityProperties.TokenProperties tokenProperties = securityProperties.getToken();
		if (StrUtil.isBlank(token) || !token.startsWith(tokenProperties.getTokenType())) {
			return null;
		}
		token = token.replace(tokenProperties.getTokenType(), "").trim();
		try {

			Jws<Claims> jws = Jwts.parser().setSigningKey(tokenProperties.getSignKey()).parseClaimsJws(token);
			Object user = jws.getBody().get("user");
			return JsonUtils.parseObject(user.toString(), LoginUser.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void storeAccessToken(LoginUser loginUser, AccessToken accessToken) {

	}

	@Override
	protected AccessToken createAccessToken(LoginUser loginUser) {
		SpringSecurityProperties.TokenProperties tokenProperties = securityProperties.getToken();

		String token = Jwts.builder().setSubject(loginUser.getUsername())
				.claim("user", JsonUtils.toJson(loginUser))
				.setExpiration(new Date(System.currentTimeMillis() + tokenProperties.getExpireTime()))
				.signWith(SignatureAlgorithm.HS512, tokenProperties.getSignKey())
				.compressWith(CompressionCodecs.GZIP).compact();
		loginUser.setPassword(null);
		AccessToken accessToken = new AccessToken();
		accessToken.setAccessToken(token);
		accessToken.setTokenType(securityProperties.getToken().getTokenType());
		accessToken.setExpiresIn(tokenProperties.getExpireTime());
		return accessToken;
	}
}
