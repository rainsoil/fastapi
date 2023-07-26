package com.github.rainsoil.fastapi.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统菜单的枚举
 *
 * @author luyanan
 * @since 2023/06/23
 **/
@AllArgsConstructor
@Getter
public enum SysMenuEnums {


	/**
	 * 根菜单id
	 *
	 * @since 2023/06/23
	 */

	ROOT_ID(null, "0", "根菜单"),
	/**
	 * 左侧菜单
	 *
	 * @since 2023/06/23
	 */

	MENU_TYPE_LEFT(Type.MENU_TYPE, "0", "左侧菜单"),

	/**
	 * 按钮
	 *
	 * @since 2023/06/23
	 */

	MENU_TYPE_BUTTON(Type.MENU_TYPE, "1", "按钮"),

	/**
	 * 顶部菜单
	 *
	 * @since 2023/06/23
	 */
	MENU_TYPE_TOP(Type.MENU_TYPE, "2", "顶部菜单"),

	;
	/**
	 * 类型
	 *
	 * @since 2023/06/23
	 */

	private Type type;

	/**
	 * 枚举的值
	 *
	 * @since 2023/06/23
	 */

	private String value;


	/**
	 * 枚举的名称
	 *
	 * @since 2023/06/23
	 */

	private String label;

	/**
	 * 枚举类型
	 *
	 * @author luyanan
	 * @since 2023/06/23
	 */
	public  enum Type {

		/**
		 * 菜单类型
		 *
		 * @since 2023/06/23
		 */

		MENU_TYPE;
	}
}
