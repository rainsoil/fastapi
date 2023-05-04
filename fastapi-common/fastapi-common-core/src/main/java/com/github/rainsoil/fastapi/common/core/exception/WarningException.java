package com.github.rainsoil.fastapi.common.core.exception;

/**
 * 警告异常
 *
 * @author luyanan
 * @since 2023/3/26/026
 */
public class WarningException extends BaseException {
    
    
    public WarningException(Integer code, String msg) {
        super(code, msg);
    }
    
    public WarningException(Integer code) {
        super(code);
    }
    
    public WarningException(String msg) {
        super(msg);
    }
}
