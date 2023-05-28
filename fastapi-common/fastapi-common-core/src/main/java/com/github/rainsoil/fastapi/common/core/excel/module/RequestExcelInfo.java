package com.github.rainsoil.fastapi.common.core.excel.module;

import com.github.rainsoil.fastapi.common.core.excel.annotation.RequestExcel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 导入excel类
 *
 * @author luyanan
 * @since 2023/05/22
 **/
public class RequestExcelInfo {

	private List<SheetInfo> sheetInfoList;

	public RequestExcelInfo(RequestExcel requestExcel) {
		this.sheetInfoList = Stream.of(requestExcel.sheets())
				.map(SheetInfo::new)
				.collect(Collectors.toList());
	}

	public RequestExcelInfo(List<SheetInfo> sheetInfoList) {
		this.sheetInfoList = sheetInfoList;
	}

	public List<SheetInfo> getSheetInfoList() {
		return sheetInfoList;
	}

	public void setSheetInfoList(List<SheetInfo> sheetInfoList) {
		this.sheetInfoList = sheetInfoList;
	}
}
