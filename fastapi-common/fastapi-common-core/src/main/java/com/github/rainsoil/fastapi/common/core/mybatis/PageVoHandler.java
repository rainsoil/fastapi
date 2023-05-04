package com.github.rainsoil.fastapi.common.core.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.rainsoil.fastapi.common.core.bean.ConvertCallBack;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 分页vo处理类
 *
 * @param <T> 实体类泛型
 * @param <V> vo类泛型
 * @author luyanan
 * @since 2023/3/28/028
 */
public interface PageVoHandler<T, V> {
    
    /**
     * 条件构造器处理
     *
     * @param t            实体类
     * @param vo           vo类
     * @param queryWrapper 条件构造器
     * @since 2023/03/28
     */
    default void queryWrapperHandler(V vo, T t, LambdaQueryWrapper<T> queryWrapper) {
        queryWrapper.setEntity(t);
    }
    
    /**
     * 获取PageInfo 返回结果
     *
     * @param page    分页参数
     * @param voClazz vo类class
     * @return cn.com.genhao2.fastapi.common.core.PageInfo<V>
     * @since 2023/03/28
     */
    default PageInfo<V> getPageInfo(Page<T> page, Class<V> voClazz) {
        PageInfo<V> pageInfo = new PageInfo<>();
        pageInfo.setCurrent(page.getCurrent());
        pageInfo.setSize(page.getSize());
        pageInfo.setPages(page.getPages());
        List<T> records = page.getRecords();
        List<V> vList = BeanConvertUtils.convertListTo(records, () -> initVo(voClazz), new ConvertCallBack<T, V>() {
            @Override
            public void callBack(T t, V s) {
                recordHandler(t, s);
            }
        });
        if (CollectionUtil.isNotEmpty(records)) {
            recordsHandler(records, vList);
        }
        pageInfo.setRecords(vList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
    
    /**
     * 初始化vo对象
     *
     * @param voClazz voclass
     * @return V
     * @since 2023/03/28
     */
    default V initVo(Class<V> voClazz) {
        V aClass = BeanUtils.instantiateClass(voClazz);
        return aClass;
    }
    
    /**
     * 分页结果处理
     *
     * @param ts 实体类
     * @param vs vo类
     * @since 2023/03/28
     */
    default void recordsHandler(List<T> ts, List<V> vs) {
    
    }
    
    
    /**
     * 单条处理
     *
     * @param t 实体类
     * @param v vo类
     * @since 2023/03/28
     */
    default void recordHandler(T t, V v) {
    
    }
    
    
    
}
