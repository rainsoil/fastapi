package com.github.rainsoil.fastapi.common.core.encryption.impl;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties;
import com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy;

import java.nio.charset.StandardCharsets;

/**
 * sm4实现对称加密
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public class Sm4SymmetricStringEncryptorStrategy implements StringEncryptorStrategy {

	/**
	 * 加密
	 *
	 * @param message   字符串
	 * @param encryptor
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	@Override
	public String encrypt(String message, EncryptorProperties.Encryptor encryptor) {
		SM4 sm4 = SmUtil.sm4(encryptor.getKey().getBytes(StandardCharsets.UTF_8));

		return sm4.encryptBase64(message);
	}

	/**
	 * 解密
	 *
	 * @param encryptedMessage 加密之后的字符串
	 * @param encryptor
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	@Override
	public String decrypt(String encryptedMessage, EncryptorProperties.Encryptor encryptor) {
		SM4 sm4 = SmUtil.sm4(encryptor.getKey().getBytes(StandardCharsets.UTF_8));
		return sm4.decryptStr(encryptedMessage);
	}
}
