package com.github.rainsoil.fastapi.common.core.excel.module;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.rainsoil.fastapi.common.core.excel.annotation.ResponseExcel;
import com.github.rainsoil.fastapi.common.core.excel.enums.Scene;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 导出的excel注解对象
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Data
public class ResponseExcelInfo {


	private String name;

	private ExcelTypeEnum suffix;

	private List<SheetInfo> sheetInfoList;

	private Scene scene;

	public ResponseExcelInfo(ResponseExcel responseExcel) {
		this.name = responseExcel.name();
		this.suffix = responseExcel.suffix();
		this.sheetInfoList = Stream.of(responseExcel.sheets())
				.map(SheetInfo::new)
				.collect(Collectors.toList());
		this.scene = responseExcel.scene();
	}

}