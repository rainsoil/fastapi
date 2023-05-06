package com.github.rainsoil.fastapi.common.core.encryption;

/**
 * 加解密
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public interface StringEncryptor {



	/**
	 * 切换加密方式
	 *
	 * @param method 加密方式
	 * @since 2023/05/06
	 */
	void switchover(String method);



	/**
	 * 加密
	 *
	 * @param message 字符串
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	String encrypt(String message);


	/**
	 * 解密
	 *
	 * @param encryptedMessage 加密之后的字符串
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	String decrypt(String encryptedMessage);
}
