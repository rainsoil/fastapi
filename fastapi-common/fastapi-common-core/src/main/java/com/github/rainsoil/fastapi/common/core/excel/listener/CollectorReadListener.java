package com.github.rainsoil.fastapi.common.core.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * excel读取监听
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Slf4j
public class CollectorReadListener extends AnalysisEventListener<Object> {
	private final Multimap<Integer, Object> multiSheetData = Multimaps.synchronizedMultimap(ArrayListMultimap.create());

	@Override
	public void invoke(Object o, AnalysisContext analysisContext) {
		Integer sheetNo = analysisContext.readSheetHolder().getSheetNo();
		multiSheetData.put(sheetNo, o);
	}

	/**
	 * 列写入
	 *
	 * @param analysisContext
	 * @since 2023/05/25
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}

	/**
	 * 列写入
	 *
	 * @return java.util.List<java.util.List < java.lang.Object>>
	 * @since 2022/12/29/029
	 */
	public List<List<Object>> sheetPartition() {
		return multiSheetData.asMap()
				.values()
				.stream()
				.map(collection -> (List<Object>) collection)
				.collect(Collectors.toList());
	}

}
