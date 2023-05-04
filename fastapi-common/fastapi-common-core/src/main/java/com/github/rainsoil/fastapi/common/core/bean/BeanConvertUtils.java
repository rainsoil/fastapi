package com.github.rainsoil.fastapi.common.core.bean;

import cn.hutool.core.collection.CollectionUtil;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Bean 转换
 *
 * @author luyanan
 * @since 2023/3/25/025
 */
@UtilityClass
public class BeanConvertUtils extends BeanUtils {
    
    
    /**
     * 对象转换
     *
     * @param source   源对象
     * @param target   目标对象
     * @param callBack 回调
     * @param <T>      目标对象泛型
     * @param <S>      源对象泛型
     * @return T 返回结果
     * @since 2023/3/25/025
     */
    public <S, T> T convertTo(S source, T target, ConvertCallBack<S, T> callBack) {
        if (null == source || null == target) {
            return null;
        }
        copyProperties(source, target);
        if (null != callBack) {
            callBack.callBack(source, target);
        }
        return target;
    }
    
    /**
     * 对象转换
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象泛型
     * @param <S>    源对象泛型
     * @return T 返回结果
     * @since 2023/3/25/025
     */
    public <S, T> T convertTo(S source, T target) {
        return convertTo(source, target, null);
    }
    
    /**
     * 对象转换
     *
     * @param source         源对象
     * @param targetSupplier 目标对象
     * @param callBack       回调
     * @param <T>            目标对象泛型
     * @param <S>            源对象泛型
     * @return T 返回结果
     * @since 2023/3/25/025
     */
    public <S, T> T convertTo(S source, Supplier<T> targetSupplier, ConvertCallBack<S, T> callBack) {
        return convertTo(source, targetSupplier.get(), callBack);
    }
    
    /**
     * 对象转换
     *
     * @param source         源对象
     * @param targetSupplier 目标对象
     * @param <T>            目标对象泛型
     * @param <S>            源对象泛型
     * @return T 返回结果
     * @since 2023/3/25/025
     */
    public <S, T> T convertTo(S source, Supplier<T> targetSupplier) {
        return convertTo(source, targetSupplier.get(), null);
    }
    
    
    /**
     * list转换
     *
     * @param sources        源对象集合
     * @param targetSupplier 目标对象几个集合
     * @param callBack       回调
     * @param <T>            目标对象泛型
     * @param <S>            源对象泛型
     * @return java.util.List<T> 转换结果
     * @since 2023/3/26/026
     */
    public <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier, ConvertCallBack<S, T> callBack) {
        
        if (CollectionUtil.isEmpty(sources)) {
            return new ArrayList<>();
        }
        List<T> targets = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = targetSupplier.get();
            BeanUtils.copyProperties(source, target);
            if (callBack != null) {
                callBack.callBack(source, target);
            }
            targets.add(target);
        }
        return targets;
    }
    
    /**
     * list转换
     *
     * @param sources        源对象集合
     * @param targetSupplier 目标对象几个集合
     * @param <T>            目标对象泛型
     * @param <S>            源对象泛型
     * @return java.util.List<T> 转换结果
     * @since 2023/3/26/026
     */
    public <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier) {
        return convertListTo(sources, targetSupplier);
    }
}
