package com.github.rainsoil.fastapi.common.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

/**
 * json工具类
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@UtilityClass
public class JsonUtils {


	/**
	 * 对象转json
	 *
	 * @param obj 对象
	 * @return java.lang.String
	 * @since 2023/05/22
	 */
	@SneakyThrows
	public String toJson(Object obj) {

		ObjectMapper objectMapper = SpringContextHolder.getBean(ObjectMapper.class);
		if (null == objectMapper) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.writeValueAsString(obj);
	}
}
