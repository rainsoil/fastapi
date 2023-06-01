package com.github.rainsoil.fastapi.common.core.excel.exception;

/**
 * excel异常
 *
 * @author luyanan
 * @since 2023/05/22
 **/
public class EasyExcelException extends RuntimeException {


	public EasyExcelException(String message) {
		super(message);
	}

	public EasyExcelException(String message, Throwable cause) {
		super(message, cause);
	}
}
