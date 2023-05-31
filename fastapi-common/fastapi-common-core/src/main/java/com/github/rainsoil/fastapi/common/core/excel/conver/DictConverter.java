package com.github.rainsoil.fastapi.common.core.excel.conver;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典值转换
 *
 * @author luyanan
 * @since 2023/05/31
 **/
@Slf4j
public class DictConverter implements Converter<String> {


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
		log.debug("dict:convertToJavaData:{}" + cellData.getStringValue());
		return cellData.getStringValue();
	}

	@Override
	public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		log.debug("dict:convertToExcelData:{}" + value);

		return new WriteCellData<>(value);
	}
}
