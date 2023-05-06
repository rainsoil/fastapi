package com.github.rainsoil.fastapi.common.core.encryption.impl;

import cn.hutool.crypto.SmUtil;
import com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties;
import com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy;

/**
 * 摘要算法
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public class Sm3DigesterStringEncryptorStrategy implements StringEncryptorStrategy {
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
		return SmUtil.sm3(message);
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

		return encryptedMessage;
	}
}
