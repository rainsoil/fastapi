package com.github.rainsoil.fastapi.core.encryptor;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * sm4密码加密器
 *
 * @author luyanan
 * @since 2023/4/4
 **/
@RequiredArgsConstructor
@Component("sm4StringEncryptor")
public class Sm4StringEncryptor implements StringEncryptor {

	private final JasyptEncryptorConfigurationProperties configurationProperties;

	@Override
	public String encrypt(String s) {
		SM4 sm4 = SmUtil.sm4(configurationProperties.getPassword().getBytes(StandardCharsets.UTF_8));
		return sm4.encryptHex(s);
	}

	@Override
	public String decrypt(String s) {
		SM4 sm4 = SmUtil.sm4(configurationProperties.getPassword().getBytes(StandardCharsets.UTF_8));
		return sm4.decryptStr(s, StandardCharsets.UTF_8);
	}
}
