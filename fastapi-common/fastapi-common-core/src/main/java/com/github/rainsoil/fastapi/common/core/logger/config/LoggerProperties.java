package com.github.rainsoil.fastapi.common.core.logger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置类
 *
 * @author luyanan
 * @since 2023/05/21
 **/
@ConfigurationProperties("spring.log")
@Data
public class LoggerProperties {
}
