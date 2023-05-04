package com.github.rainsoil.fastapi.common.core.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * spring的配置类
 *
 * @author luyanan
 * @since 2023/3/28
 **/
@Configuration
@Import(SpringContextHolder.class)
public class SpringAutoConfiguration {
}
