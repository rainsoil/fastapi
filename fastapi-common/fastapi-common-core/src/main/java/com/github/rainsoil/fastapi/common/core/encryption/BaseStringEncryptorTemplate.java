package com.github.rainsoil.fastapi.common.core.encryption;

import cn.hutool.core.lang.Opt;
import com.github.rainsoil.fastapi.common.core.encryption.impl.Sm2AsymmetricStringEncryptorStrategy;
import com.github.rainsoil.fastapi.common.core.encryption.impl.Sm3DigesterStringEncryptorStrategy;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;

/**
 * 基础加解密类实现
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public abstract class BaseStringEncryptorTemplate implements StringEncryptor {

	private EncryptorProperties encryptorProperties;

	private StringEncryptorStrategy strategy;
	private EncryptorProperties.Encryptor encryptor;

	/**
	 * 支持的加密方式
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.EncryptionEnums
	 * @since 2023/05/06
	 */
	protected abstract EncryptionEnums support();

	/**
	 * 切换加密方式
	 *
	 * @param method 加密方式
	 * @since 2023/05/06
	 */
	@Override
	public void switchover(String method) {
		EncryptionEnums encryptionEnums = support();

		method = Opt.ofBlankAble(method).orElse(EncryptorProperties.DEFAULT_BEAN_KEY);
		EncryptorProperties.Encryptor encryptor = null;
		switch (encryptionEnums) {
			case DIGESTER:
				encryptor = encryptorProperties.getDigesterMap().get(method);
				if (null == encryptor.getBean()) {
					encryptor.setBean(Sm3DigesterStringEncryptorStrategy.class);
				}
				break;
			case SYMMETRIC:
				encryptor = encryptorProperties.getSymmetricMap().get(method);
				if (null == encryptor.getBean()) {
					encryptor.setBean(Sm3DigesterStringEncryptorStrategy.class);
				}
				break;
			case ASYMMETRIC:
				encryptor = encryptorProperties.getAsymmetricMap().get(method);
				if (null == encryptor.getBean()) {
					encryptor.setBean(Sm2AsymmetricStringEncryptorStrategy.class);
				}
				break;
			default:
				break;
		}
		StringEncryptorStrategy strategy = SpringContextHolder.getBean(encryptor.getBean());
		this.encryptor = encryptor;
		this.strategy = strategy;
	}

	/**
	 * 加密
	 *
	 * @param message 字符串
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	@Override
	public String encrypt(String message) {
		return this.strategy.encrypt(message, encryptor);
	}

	/**
	 * 解密
	 *
	 * @param encryptedMessage 加密之后的字符串
	 * @return java.lang.String
	 * @since 2023/05/06
	 */
	@Override
	public String decrypt(String encryptedMessage) {
		return this.strategy.decrypt(encryptedMessage, encryptor);
	}
}
