package com.github.rainsoil.fastapi.common.core.encryption.template;

import com.github.rainsoil.fastapi.common.core.encryption.BaseStringEncryptorTemplate;
import com.github.rainsoil.fastapi.common.core.encryption.EncryptionEnums;

/**
 * 摘要算法加解密
 *
 * @author luyanan
 * @since 2023/05/06
 **/
public class DigesterStringEncryptorTemplate extends BaseStringEncryptorTemplate {
	/**
	 * 支持的加密方式
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.EncryptionEnums
	 * @since 2023/05/06
	 */
	@Override
	protected EncryptionEnums support() {
		return EncryptionEnums.DIGESTER;
	}
}
