package com.github.rainsoil.fastapi.common.core.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制层
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Slf4j
@RestController
public class TestController {
	@GetMapping("/test")
	public String testController(String name) {
		log.debug("test:{}", name);
		return name;
	}
}
