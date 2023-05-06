package com.github.rainsoil.fastapi.common.core.encryption;

import com.github.rainsoil.fastapi.common.core.encryption.impl.Sm2AsymmetricStringEncryptorStrategy;
import com.github.rainsoil.fastapi.common.core.encryption.impl.Sm3DigesterStringEncryptorStrategy;
import com.github.rainsoil.fastapi.common.core.encryption.impl.Sm4SymmetricStringEncryptorStrategy;
import com.github.rainsoil.fastapi.common.core.encryption.template.AsymmetricStringEncryptorTemplate;
import com.github.rainsoil.fastapi.common.core.encryption.template.DigesterStringEncryptorTemplate;
import com.github.rainsoil.fastapi.common.core.encryption.template.SymmetricStringEncryptorTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加解密自动化配置类
 *
 * @author luyanan
 * @since 2023/05/06
 **/
@Configuration
public class EncryptionConfiguration {


	/**
	 * sm4加解密
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy
	 * @since 2023/05/06
	 */
	@ConditionalOnMissingBean
	@Bean("sm4Symmetric")
	public StringEncryptorStrategy sm4SymmetricStringEncryptorStrategy() {
		return new Sm4SymmetricStringEncryptorStrategy();
	}


	/**
	 * sm3加密
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy
	 * @since 2023/05/06
	 */
	@ConditionalOnMissingBean
	@Bean("sm3Digester")
	public StringEncryptorStrategy sm3DigesterStringEncryptorStrategy() {
		return new Sm3DigesterStringEncryptorStrategy();
	}


	/**
	 * sm2摘要算法
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptorStrategy
	 * @since 2023/05/06
	 */
	@ConditionalOnMissingBean
	@Bean("sm2Asymmetric")
	public StringEncryptorStrategy sm2AsymmetricStringEncryptorStrategy() {
		return new Sm2AsymmetricStringEncryptorStrategy();
	}

	/**
	 * 对称加密模板类
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptor
	 * @since 2023/05/06
	 */
	@Bean
	public StringEncryptor symmetricStringEncryptorTemplate() {
		return new SymmetricStringEncryptorTemplate();
	}


	/**
	 * 非对称加密模板类
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptor
	 * @since 2023/05/06
	 */
	@Bean
	public StringEncryptor asymmetricStringEncryptorTemplate() {
		return new AsymmetricStringEncryptorTemplate();
	}


	/**
	 * 摘要算法加解密
	 *
	 * @return com.github.rainsoil.fastapi.common.core.encryption.StringEncryptor
	 * @since 2023/05/06
	 */
	@Bean
	public StringEncryptor digesterStringEncryptorTemplate() {
		return new DigesterStringEncryptorTemplate();
	}
}
