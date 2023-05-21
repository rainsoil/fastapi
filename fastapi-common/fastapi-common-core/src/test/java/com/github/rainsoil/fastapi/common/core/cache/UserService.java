package com.github.rainsoil.fastapi.common.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户service
 *
 * @author luyanan
 * @since 2023/05/21
 **/
@Slf4j
@Service
public class UserService {


	@Cacheable(cacheNames = "usercache#10s")
	public String name() {
		log.debug("模拟从db查询");
		return "张三";
	}
}
