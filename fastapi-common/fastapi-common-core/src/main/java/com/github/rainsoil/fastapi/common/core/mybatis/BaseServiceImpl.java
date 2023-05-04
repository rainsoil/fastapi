package com.github.rainsoil.fastapi.common.core.mybatis;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 基础service 实现类
 *
 * @param <T> 实体类泛型
 * @param <M> baseMapper
 * @author luyanan
 * @since 2023/3/28/028
 */
public class BaseServiceImpl<M extends BaseBaseMapper<T>, T> extends MPJBaseServiceImpl<M, T>
        implements IBaseService<T> {
    
    @Override
    public PageInfo<T> page(PageRequest<T> pageRequest, PageHandler<T> pageHandler) {
        Page<T> page = getPage(pageRequest);
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        T param = pageRequest.getParam();
        pageHandler.queryWrapperHandler(param, queryWrapper);
        
        queryWrapper.setEntity(param);
        Page<T> resultPage = this.page(page, queryWrapper);
        PageInfo<T> pageInfo = pageHandler.getPageInfo(resultPage);
        return pageInfo;
    }
    
    @Override
    public <V> PageInfo<V> pageVo(PageRequest pageRequest, Class<V> voClass, PageVoHandler<T, V> pageVoHandler) {
        Page<T> page = getPage(pageRequest);
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        V v = pageVoHandler.initVo(voClass);
        Object param = pageRequest.getParam();
        T t = BeanConvertUtils.instantiateClass(currentModelClass());
        if (null != param) {
            
            if (param instanceof Map) {
                t = BeanUtil.fillBeanWithMap((Map<?, ?>) param, t, false);
            } else {
                T finalT = t;
                t = BeanConvertUtils.convertTo(pageRequest.getParam(), () -> finalT);
            }
        }
        queryWrapper.setEntity(t);
        pageVoHandler.queryWrapperHandler(v, t, queryWrapper);
        Page<T> resultPage = this.page(page, queryWrapper);
        return pageVoHandler.getPageInfo(resultPage, voClass);
    }
    
    
    /**
     * 获取PageInfo对象
     *
     * @param page 分页参数
     * @param <V>  vo类泛型
     * @return cn.com.genhao2.fastapi.common.core.PageInfo
     * @since 2023/03/28
     */
    public <V> PageInfo getPageInfo(Page<V> page) {
        PageInfo<V> pageInfo = new PageInfo<>();
        pageInfo.setCurrent(page.getCurrent());
        pageInfo.setSize(page.getSize());
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        List<V> records = page.getRecords();
        pageInfo.setRecords(records);
        return pageInfo;
    }
}
