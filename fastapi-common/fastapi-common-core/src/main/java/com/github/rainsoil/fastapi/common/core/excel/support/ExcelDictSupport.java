package com.github.rainsoil.fastapi.common.core.excel.support;

/**
 * excel 字典值
 *
 * @author luyanan
 * @since 2023/06/01
 **/
public interface ExcelDictSupport {


	/**
	 * 根据value值获取
	 *
	 * @param type  字典type
	 * @param value 字典值
	 * @return java.lang.String
	 * @since 2023/06/01
	 */
	String getLabelByValue(String type, String value);


	/**
	 * 根据字典内容 获取字典值
	 *
	 * @param type  字典type
	 * @param label 字典内容
	 * @return java.lang.String
	 * @since 2023/06/01
	 */
	String getValueByLabel(String type, String label);

}
