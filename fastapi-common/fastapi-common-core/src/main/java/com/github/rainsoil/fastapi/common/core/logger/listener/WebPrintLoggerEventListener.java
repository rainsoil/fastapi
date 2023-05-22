package com.github.rainsoil.fastapi.common.core.logger.listener;

import com.github.rainsoil.fastapi.common.core.logger.config.LoggerProperties;
import com.github.rainsoil.fastapi.common.core.logger.core.AbstractLoggerParserHandler;
import com.github.rainsoil.fastapi.common.core.logger.core.LoggerEvent;
import com.github.rainsoil.fastapi.common.core.logger.core.LoggerEventDto;
import com.github.rainsoil.fastapi.common.core.logger.core.LoggerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 日志打印 事件监听
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = LoggerProperties.PREFIX, value = "print", havingValue = "true", matchIfMissing = false)
@Configuration(proxyBeanMethods = false)
public class WebPrintLoggerEventListener extends AbstractLoggerParserHandler implements ApplicationListener<LoggerEvent> {
	@Override
	public void onApplicationEvent(LoggerEvent loggerEvent) {

		if (null == loggerEvent || !(loggerEvent.getSource() instanceof LoggerEventDto)) {
			return;
		}

		LoggerEventDto loggerEventDto = (LoggerEventDto) loggerEvent.getSource();


		LoggerInfo loggerInfo = parserToLoggerInfo(loggerEventDto);

		StringBuffer sb = new StringBuffer();
		sb.append("\n[-----------------------------------").append("\n")
				.append("methodName:").append(loggerInfo.getMethodName()).append("\n")
				.append("sourceIp:").append(loggerInfo.getSourceIp()).append("\n")
				.append("desp:").append(loggerInfo.getDesp()).append("\n")
				.append("uri:").append(loggerInfo.getUri()).append("\n");
		if (null != loggerInfo.getRequestParams()) {
			sb.append("requestParams:").append(loggerInfo.getRequestParams()).append("\n");
		}

		if (null != loggerInfo.getTimeConsuming()) {
			sb.append("timeConsuming:").append(loggerInfo.getTimeConsuming()).append("\n");
		}
		if (null != loggerInfo.getResult()) {
			sb.append("result:").append(loggerInfo.getResult()).append("\n");
		}
		if (null != loggerInfo.getErrorMsg()) {
			sb.append("errorMsg:").append(loggerInfo.getErrorMsg()).append("\n");
		}
		sb.append("------------------------------]");
		log.debug(sb.toString());
	}
}
