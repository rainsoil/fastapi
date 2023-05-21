package com.github.rainsoil.fastapi.common.core.logger.core;

import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author luyanan
 * @since 2023/05/21
 **/
public class LoggerEvent extends ApplicationEvent {
	public LoggerEvent(Object source) {
		super(source);
	}
}
