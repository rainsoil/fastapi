package com.github.rainsoil.fastapi.common.core.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;

import java.util.List;

/**
 * 分页处理类
 *
 * @param <T> 实体类泛型
 * @author luyanan
 * @since 2023/3/28
 **/
public interface PageHandler<T> {


	/**
	 * 条件构造处理类
	 *
	 * @param param        实体类参数
	 * @param queryWrapper 条件构造
	 * @since 2023/3/28
	 */
	default void queryWrapperHandler(T param, LambdaQueryWrapper<T> queryWrapper) {

	}

	/**
	 * 获取PageInfo 返回结果
	 *
	 * @param page 分页结果
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<T>
	 * @since 2023/3/28
	 */
	default PageInfo<T> getPageInfo(Page<T> page) {

		PageInfo<T> pageInfo = new PageInfo<>();
		pageInfo.setCurrent(page.getCurrent());
		pageInfo.setSize(page.getSize());
		pageInfo.setPages(page.getPages());
		List<T> records = page.getRecords();
		if (CollectionUtil.isNotEmpty(records)) {
			recordsHandler(records);
			for (T record : records) {
				recordHandler(record);
			}
		}

		pageInfo.setRecords(records);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	/**
	 * 列表数据处理
	 *
	 * @param records 列表数据
	 * @since 2023/3/28
	 */
	default void recordsHandler(List<T> records) {

	}


	/**
	 * 每行结果处理
	 *
	 * @param t 实体类
	 * @since 2023/3/28
	 */
	default void recordHandler(T t) {

	}
}
