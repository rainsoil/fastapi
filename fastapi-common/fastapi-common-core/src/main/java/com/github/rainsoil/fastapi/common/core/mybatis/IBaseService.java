package com.github.rainsoil.fastapi.common.core.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.yulichang.base.MPJBaseService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 基础Service
 *
 * @param <T> 实体类泛型
 * @author luyanan
 * @since 2023/3/28/028
 */
public interface IBaseService<T> extends MPJBaseService<T> {


	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @param pageHandler 分页结果处理类
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<T>
	 * @since 2023/3/28/028
	 */
	PageInfo<T> page(PageRequest<T> pageRequest, PageHandler<T> pageHandler);


	/**
	 * 获取分页结果
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<T>
	 * @since 2023/3/28/028
	 */
	default PageInfo<T> page(PageRequest<T> pageRequest) {
		return page(pageRequest, new PageHandler<T>() {
		});
	}


	/**
	 * vo类获取分页结果
	 *
	 * @param <V>           vo类泛型
	 * @param pageRequest   分页参数
	 * @param voClass       vo类class
	 * @param pageVoHandler 结果转换
	 * @return com.github.rainsoil.fastapi.common.core.PageInfo<V>
	 * @since 2023/3/28/028
	 */
	<V> PageInfo<V> pageVo(PageRequest pageRequest, Class<V> voClass, PageVoHandler<T, V> pageVoHandler);

	/**
	 * vo类获取分页结果
	 *
	 * @param pageRequest 分页参数
	 * @param voClass     class
	 * @param <V>         vo类泛型
	 * @return cn.com.genhao2.fastapi.common.core.PageInfo<V>
	 * @since 2023/3/28/028
	 */
	default <V> PageInfo<V> pageVo(PageRequest pageRequest, Class<V> voClass) {
		return pageVo(pageRequest, voClass, new PageVoHandler<T, V>() {
		});
	}

	/**
	 * 删除用户
	 *
	 * @param id
	 * @return boolean
	 * @since 2022/10/20
	 */
	@Override
	default boolean removeById(Serializable id) {
		return this.removeById(id, true);
	}


	@Override
	default List<T> listByIds(Collection<? extends Serializable> idList) {
		if (CollectionUtil.isEmpty(idList)) {
			return new ArrayList<>();
		}
		return this.getBaseMapper().selectBatchIds(idList);
	}


	/**
	 * 获取Page分页实体
	 *
	 * @param pageRequest 分页参数
	 * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page
	 * @since 2023/3/28/028
	 */
	default Page getPage(PageRequest pageRequest) {
		Page page = new Page<>();
		page.setCurrent(pageRequest.getCurrent());
		page.setSize(pageRequest.getSize());
		return page;
	}


	@Override
	default T getOne(Wrapper<T> queryWrapper) {
		return this.getOne(queryWrapper, false);
	}
}
