package com.github.rainsoil.fastapi.common.core;

import lombok.Data;


/**
 * 分页请求
 *
 * @param <T> 请求参数泛型
 * @author luyanan
 * @since 2023/3/27/027
 */
@Data
public class PageRequest<T> {
    
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
     * 参数
     *
     * @since 2023/3/27/027
     */
    
    private T param;
}
