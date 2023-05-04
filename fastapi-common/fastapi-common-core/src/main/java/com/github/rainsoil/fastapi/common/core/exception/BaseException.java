package com.github.rainsoil.fastapi.common.core.exception;

/**
 * 基础异常类
 *
 * @author luyanan
 * @since 2023/3/26/026
 */
public class BaseException extends RuntimeException {
    
    
    /**
     * 错误编码
     *
     * @since 2023/3/26/026
     */
    
    private Integer code;
    
    
    /**
     * 错误信息
     *
     * @since 2023/3/26/026
     */
    
    private String msg;
    
    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    
    
    public BaseException(Integer code) {
        super(code.toString());
        this.code = code;
    }
    
    
    public BaseException(String msg) {
        this.code = GlobalMsgCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
}
