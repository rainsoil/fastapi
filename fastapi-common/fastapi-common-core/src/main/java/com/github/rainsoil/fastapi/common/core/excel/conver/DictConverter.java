package com.github.rainsoil.fastapi.common.core.excel.conver;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.github.rainsoil.fastapi.common.core.excel.annotation.ExcelDict;
import com.github.rainsoil.fastapi.common.core.excel.support.ExcelDictSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典值转换
 *
 * @author luyanan
 * @since 2023/05/31
 **/
@Slf4j
@RequiredArgsConstructor
public class DictConverter implements Converter<String> {

	private final ExcelDictSupport excelDictSupport;

	@Override
	public Class<?> supportJavaTypeKey() {
		return String.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		if (contentProperty.getField().isAnnotationPresent(ExcelDict.class)) {
			ExcelDict excelDict = contentProperty.getField().getAnnotation(ExcelDict.class);
			return excelDictSupport.getValueByLabel(excelDict.value(), cellData.getStringValue());
		}
		return cellData.getStringValue();
	}

	@Override
	public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		if (contentProperty.getField().isAnnotationPresent(ExcelDict.class)) {
			ExcelDict excelDict = contentProperty.getField().getAnnotation(ExcelDict.class);
			return new WriteCellData<>(excelDictSupport.getLabelByValue(excelDict.value(), value));
		}
		return new WriteCellData<>(value);
	}
}
