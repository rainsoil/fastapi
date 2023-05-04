package com.github.rainsoil.fastapi.common.core.exception;

/**
 * 错误提醒
 *
 * @author luyanan
 * @since 2023/3/26/026
 */
public class ErrorException extends BaseException {
    
    public ErrorException(Integer code, String msg) {
        super(code, msg);
    }
    
    public ErrorException(Integer code) {
        super(code);
    }
    
    public ErrorException(String msg) {
        super(msg);
    }
}

