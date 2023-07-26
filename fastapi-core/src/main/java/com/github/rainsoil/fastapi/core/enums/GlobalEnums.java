package com.github.rainsoil.fastapi.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局枚举
 *
 * @author luyanan
 * @since 2023/06/09
 **/
@Getter
@AllArgsConstructor
public enum GlobalEnums {

	/**
	 * 正常
	 *
	 * @since 2023/06/09
	 */

	STATUS_ENABLE(Type.STATUS, "0", "正常"),

	/**
	 * 冻结
	 *
	 * @since 2023/06/09
	 */

	STATUS_FREEZE(Type.STATUS, "1", "冻结"),

	;

	/**
	 * 枚举类型
	 *
	 * @since 2023/06/09
	 */

	private Type type;


	/**
	 * 枚举的值
	 *
	 * @since 2023/06/09
	 */

	private String value;


	/**
	 * 枚举的标签
	 *
	 * @since 2023/06/09
	 */

	private String label;

	/**
	 * 枚举type
	 *
	 * @author luyanan
	 * @since 2023/06/09
	 */
	public  enum Type {


		/**
		 * 状态
		 *
		 * @since 2023/06/09
		 */

		STATUS,
	}
}
