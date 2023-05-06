package com.github.rainsoil.fastapi.common.core.encryption;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 加解密配置
 *
 * @author luyanan
 * @since 2023/05/06
 **/
@Data
public class EncryptorProperties {

	/**
	 * 默认bean的key
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	public static final String DEFAULT_BEAN_KEY = "default";


	/**
	 * 对称加密配置
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Encryptor symmetric = new Encryptor();


	/**
	 * 非对称加密配置
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Encryptor asymmetric;


	/**
	 * 摘要算法实现的bean
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Encryptor digester;


	/**
	 * 多个对称加密配置
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Map<String, Encryptor> symmetricMap = new HashMap<>();


	/**
	 * 多个非对称加密配置
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Map<String, Encryptor> asymmetricMap = new HashMap<>();


	/**
	 * 摘要算法配置
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	private Map<String, Encryptor> digesterMap = new HashMap<>();

	/**
	 * 密码
	 *
	 * @author luyanan
	 * @since 2023/05/06
	 */
	@Data
	@NoArgsConstructor
	public static class Encryptor {
		/**
		 * 私钥
		 *
		 * @author luyanan
		 * @since 2023/05/06
		 */
		private String privateKey;
		/**
		 * 公钥
		 *
		 * @author luyanan
		 * @since 2023/05/06
		 */
		private String publicKey;

		/**
		 * 加密的key
		 *
		 * @author luyanan
		 * @since 2023/05/06
		 */
		private String key;
		/**
		 * 实现的bean
		 *
		 * @author luyanan
		 * @since 2023/05/06
		 */
		private Class<? extends StringEncryptorStrategy> bean;


		public Encryptor(Class<? extends StringEncryptorStrategy> bean) {
			this.bean = bean;
		}
	}


	/**
	 * 获取对称加密
	 *
	 * @return java.util.Map<java.lang.String, com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties.Encryptor>
	 * @since 2023/05/06
	 */
	public Map<String, Encryptor> getSymmetricMap() {

		if (null != this.symmetric) {
			this.symmetricMap.put(DEFAULT_BEAN_KEY, this.symmetric);
		}
		return symmetricMap;
	}

	/**
	 * 非对称加密
	 *
	 * @return java.util.Map<java.lang.String, com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties.Encryptor>
	 * @since 2023/05/06
	 */
	public Map<String, Encryptor> getAsymmetricMap() {
		if (null != this.asymmetric) {
			this.asymmetricMap.put(DEFAULT_BEAN_KEY, this.asymmetric);
		}
		return asymmetricMap;
	}


	/**
	 * 摘要算法
	 *
	 * @return java.util.Map<java.lang.String, com.github.rainsoil.fastapi.common.core.encryption.EncryptorProperties.Encryptor>
	 * @since 2023/05/06
	 */
	public Map<String, Encryptor> getDigesterMap() {
		if (null != this.digester) {
			this.digesterMap.put(DEFAULT_BEAN_KEY, this.digester);
		}
		return digesterMap;
	}
}
