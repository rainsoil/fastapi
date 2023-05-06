package com.github.rainsoil.fastapi.common.core.encryption.impl;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties;
import com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy;

/**
 * sm3 实现非对称加密
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public class Sm2AsymmetricStringEncryptorStrategy implements StringEncryptorStrategy {

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
		SM2 sm2 = new SM2(encryptor.getPrivateKey(), encryptor.getPublicKey());
		return sm2.encryptBase64(message, KeyType.PublicKey);
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
		SM2 sm2 = new SM2(encryptor.getPrivateKey(), encryptor.getPublicKey());
		return sm2.decryptStr(encryptedMessage, KeyType.PrivateKey);
	}
}
