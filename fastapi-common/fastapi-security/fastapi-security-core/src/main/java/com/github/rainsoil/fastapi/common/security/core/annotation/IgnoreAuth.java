package com.github.rainsoil.fastapi.common.security.core.annotation;

import java.lang.annotation.*;

/**
 * 忽略授权注解
 *
 * @author luyanan
 * @since 2023/06/04
 **/

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface IgnoreAuth {
}
