package com.github.rainsoil.fastapi.core.security;

import com.github.rainsoil.fastapi.common.core.encryption.template.DigesterStringEncryptorTemplate;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密器
 *
 * @author luyanan
 * @since 2023/06/11
 **/
@RequiredArgsConstructor
@Component
public class Sm3PasswordEncoder implements PasswordEncoder {



	@Override
	public String encode(CharSequence rawPassword) {
		DigesterStringEncryptorTemplate digesterStringEncryptorTemplate = SpringContextHolder.getBean(DigesterStringEncryptorTemplate.class);
		String encrypt = digesterStringEncryptorTemplate.encrypt(String.valueOf(rawPassword));
		return encrypt;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
}
