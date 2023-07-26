package com.github.rainsoil.fastapi.core.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自定义字段填充
 *
 * @author luyanan
 * @since 2023/04/03
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {

		log.debug("insertFill");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.debug("updateFill");
	}
}
