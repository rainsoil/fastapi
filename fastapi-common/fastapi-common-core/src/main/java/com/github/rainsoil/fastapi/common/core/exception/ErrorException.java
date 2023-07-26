package com.github.rainsoil.fastapi.common.core.exception;

/**
 * 错误提醒
 *
 * @author luyanan
 * @since 2023/3/26/026
 */
public class ErrorException extends BaseException {


	public ErrorException(String msg, Integer code, Object... args) {
		super(msg, code, args);
	}

	public ErrorException(Integer code, Object... args) {
		super(code, args);
	}

	public ErrorException(String msg, Object... args) {
		super(msg, args);
	}
}

