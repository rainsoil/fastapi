package com.github.rainsoil.fastapi.common.core.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

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

	/**
	 * json 转实体
	 *
	 * @param json  json
	 * @param clazz 实体类class
	 * @param <T>   实体类泛型
	 * @return T 实体类
	 * @since 2023/06/07
	 */
	public static <T> T parseObject(String json, Class<T> clazz) throws JsonProcessingException {

		if (StrUtil.isBlank(json)) {
			return null;
		}
		ObjectMapper objectMapper = SpringContextHolder.getBean(ObjectMapper.class);
		if (null == objectMapper) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper.readValue(json, clazz);
	}
}
