package com.github.rainsoil.fastapi.common.core.excel;

import com.github.rainsoil.fastapi.common.core.excel.support.ExcelDictSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 字典转换
 *
 * @author luyanan
 * @since 2023/06/01
 **/
@Component
public class ExcelDictSupportImpl implements ExcelDictSupport {
	/**
	 * 根据value值获取
	 *
	 * @param type  字典type
	 * @param value 字典值
	 * @return java.lang.String
	 * @since 2023/06/01
	 */
	@Override
	public String getLabelByValue(String type, String value) {

		return Arrays.stream(SexEnums.values()).filter(a -> a.getValue().equals(value)).findFirst().orElse(SexEnums.UNKNOWN).getLabel();
	}

	/**
	 * 根据字典内容 获取字典值
	 *
	 * @param type  字典type
	 * @param label 字典内容
	 * @return java.lang.String
	 * @since 2023/06/01
	 */
	@Override
	public String getValueByLabel(String type, String label) {
		return Arrays.stream(SexEnums.values()).filter(a -> a.getLabel().equals(label)).findFirst().orElse(SexEnums.UNKNOWN).getValue();
	}

	@Getter
	@AllArgsConstructor
	public static enum SexEnums {

		MALE("男", "1"),
		FEMALE("女", "0"),
		UNKNOWN("未知", "-1");

		/**
		 * 字典内容
		 *
		 * @since 2023/06/01
		 */

		private String label;
		/**
		 * 字典值
		 *
		 * @since 2023/06/01
		 */

		private String value;
	}
}
