package com.github.rainsoil.fastapi.common.core.encryption;

/**
 * 加解密策略
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public interface StringEncryptorStrategy {


	/**
	 * 加密
	 *
	 * @param message   字符串
	 * @param encryptor 配置文件
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	String encrypt(String message, EncryptorProperties.Encryptor encryptor);


	/**
	 * 解密
	 * @param encryptor 配置文件
	 * @param encryptedMessage 加密之后的字符串
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	String decrypt(String encryptedMessage, EncryptorProperties.Encryptor encryptor);


}
