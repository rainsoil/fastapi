package com.github.rainsoil.fastapi.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页返回
 *
 * @param <T> 返回泛型
 * @author luyanan
 * @since 2023/3/27/027
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {
    
    /**
     * 当前页数
     *
     * @since 2023/3/27/027
     */
    
    private long current;
    
    /**
     * 每页条数
     *
     * @since 2023/3/27/027
     */
    
    private long size;
    
    /**
     * 返回内容
     *
     * @since 2023/3/27/027
     */
    
    private List<T> records;
    
    /**
     * 总条数
     *
     * @since 2023/3/27/027
     */
    
    private long total;
    
    
    /**
     * 总页数
     *
     * @since 2023/3/27/027
     */
    
    private long pages;
    
    
    /**
     * 扩充字段
     *
     * @since 2023/3/27/027
     */
    
    private Map<String, Object> ext = new HashMap<>();
    
    
    /**
     * 空的分页返回
     *
     * @param <T>         返回泛型
     * @param pageRequest 分页请求参数
     * @return com.github.rainsoil.fastapi.common.core.PageInfo<T>
     * @since 2023/3/27/027
     */
    public static <T> PageInfo<T> empty(PageRequest pageRequest) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setRecords(new ArrayList<>());
        pageInfo.setTotal(0);
        pageInfo.setCurrent(pageRequest.getCurrent());
        pageInfo.setSize(pageRequest.getSize());
        pageInfo.setPages(0);
        return pageInfo;
    }
    
    /**
     * 分页返回
     *
     * @param <T>         分页返回对象泛型
     * @param pageRequest 分页请求参数
     * @param records     返回内容
     * @param total       总条数
     * @return com.github.rainsoil.fastapi.common.core.PageInfo<T>
     * @since 2023/3/27/027
     */
    public static <T> PageInfo<T> page(PageRequest pageRequest, List<T> records, long total) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setRecords(records);
        pageInfo.setTotal(total);
        pageInfo.setCurrent(pageRequest.getCurrent());
        pageInfo.setSize(pageRequest.getSize());
        pageInfo.setPages((total / pageInfo.getSize()) + 1);
        return pageInfo;
    }
}
