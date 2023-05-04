package com.github.rainsoil.fastapi.common.core;

import com.github.rainsoil.fastapi.common.core.exception.BaseException;
import com.github.rainsoil.fastapi.common.core.exception.GlobalMsgCode;
import lombok.Data;

import java.util.function.Supplier;

/**
 * 响应返回
 *
 * @param <T> 返回泛型
 * @author luyanan
 * @since 2023/3/27/027
 */
@Data
public class R<T> {
    
    
    /**
     * 返回信息
     *
     * @since 2023/3/27/027
     */
    
    private String msg;
    
    
    /**
     * 返回数据
     *
     * @since 2023/3/27/027
     */
    
    private T data;
    
    
    /**
     * 返回状态
     *
     * @since 2023/3/27/027
     */
    
    
    private Integer status;
    
    /**
     * 默认成功的状态
     *
     * @since 2023/3/27/027
     */
    
    private static final Integer SUCCESS_STATUS = GlobalMsgCode.SUCCESS;
    
    /**
     * 默认失败的状态
     *
     * @since 2023/3/27/027
     */
    
    private static final Integer ERROR_STATUS = GlobalMsgCode.INTERNAL_SERVER_ERROR;
    
    
    /**
     * 成功
     *
     * @param <T>  返回体泛型
     * @param data 数据
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T> R<T> ok(T data) {
        R r = new R();
        r.setStatus(SUCCESS_STATUS);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }
    
    
    /**
     * 成功
     *
     * @param <T> 返回体泛型
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T> R<T> ok() {
        return ok(null);
    }
    
    
    /**
     * 断言是否为真，如果为 false 抛出给定的异常
     *
     * @param expression 表达式
     * @param supplier   指定断言不通过时抛出的异常
     * @param <T>        返回体
     * @param <X>        异常
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T, X extends BaseException> R<T> isTrue(boolean expression, Supplier<? extends X> supplier) {
        if (expression) {
            return R.ok();
        } else {
            throw supplier.get();
        }
    }
    
    
    /**
     * 断言是否为真，如果为 false 抛出给定的异常
     *
     * @param <T>        返回体泛型
     * @param <X>        异常泛型
     * @param expression 表达式
     * @param errorCode  不通过的时候抛出来的的异常值
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T, X extends BaseException> R<T> isTrue(boolean expression, Integer errorCode) {
        if (expression) {
            return R.ok();
        } else {
            throw new BaseException(errorCode);
        }
    }
    
    
    /**
     * 断言是否为真，如果为 false 抛出给定的异常
     *
     * @param expression 表达式
     * @param errormsg   不通过的时候跑出来的的错误提示
     * @param <T>        返回体泛型
     * @param <X>        异常泛型
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T, X extends BaseException> R<T> isTrue(boolean expression, String errormsg) {
        if (expression) {
            return R.ok();
        } else {
            throw new BaseException(errormsg);
        }
    }
    
    
    /**
     * 失败
     *
     * @param <T> 泛型
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T> R<T> failed() {
        return restResult(null, ERROR_STATUS, null);
    }
    
    
    /**
     * 失败
     *
     * @param msg 失败信息
     * @param <T> 返回结果泛型
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    public static <T> R<T> failed(String msg) {
        return restResult(null, ERROR_STATUS, msg);
    }
    
    
    /**
     * 失败
     *
     * @param status 状态码
     * @param msg    失败消息
     * @param <T>    返回结果泛型
     * @return com.github.rainsoil.fastapi.common.core.R
     * @since 2023/3/27/027
     */
    public static <T> R failed(Integer status, String msg) {
        R r = new R();
        r.setStatus(status);
        r.setMsg(msg);
        return r;
    }
    
    
    /**
     * 设置R对象
     *
     * @param data 数据
     * @param code 状态码
     * @param msg  消息
     * @param <T>  返回对象泛型
     * @return com.github.rainsoil.fastapi.common.core.R<T>
     * @since 2023/3/27/027
     */
    private static <T> R<T> restResult(T data, Integer code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setData(data);
        apiResult.setStatus(code);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
