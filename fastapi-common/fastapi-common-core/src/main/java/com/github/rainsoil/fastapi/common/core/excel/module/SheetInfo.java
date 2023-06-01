package com.github.rainsoil.fastapi.common.core.excel.module;

import com.github.rainsoil.fastapi.common.core.excel.annotation.Sheet;
import lombok.Data;

/**
 * sheet信息
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Data
public class SheetInfo {
	/**
	 * 名称
	 *
	 * @author luyanan
	 * @since 2023/05/22
	 */
	private String name;


	/**
	 * 下标
	 *
	 * @author luyanan
	 * @since 2023/05/22
	 */
	private int index;


	/**
	 * 实体类对象
	 *
	 * @author luyanan
	 * @since 2023/05/22
	 */
	private Class<?> headClazz;


	/**
	 * 标题的行号
	 *
	 * @author luyanan
	 * @since 2023/05/22
	 */
	private int headRowNumber;


	public SheetInfo(Sheet sheet) {
		this.headClazz = sheet.headClazz();
		this.name = sheet.name();
		this.headClazz = sheet.headClazz();
		this.headRowNumber = sheet.headRowNumber();
	}
}
