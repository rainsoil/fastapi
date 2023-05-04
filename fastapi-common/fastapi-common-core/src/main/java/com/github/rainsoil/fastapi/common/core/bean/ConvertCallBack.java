package com.github.rainsoil.fastapi.common.core.bean;

/**
 * bean 转换回调
 *
 * @param <S> 源对象泛型
 * @param <T> 目标对象泛型
 * @author luyanan
 * @since 2023/3/25/025
 */
@FunctionalInterface
public interface ConvertCallBack<S, T> {
    
    /**
     * 回调
     *
     * @param s 源对象
     * @param t 目标对象
     * @since 2023/3/25/025
     */
    void callBack(S s, T t);
}
