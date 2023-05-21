package com.github.rainsoil.fastapi.common.core.cache.caffeine;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.rainsoil.fastapi.common.core.cache.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableCaching
public class CaffeineAutoCacheManagerTest {

	@Autowired
	private UserService userService;

	@Test
	public void cacheTest() throws InterruptedException {
		for (int i = 0; i < 100; i++) {

			log.debug(DateUtil.format(new Date(), DatePattern.UTC_SIMPLE_PATTERN) + ":调用name");
			String name = userService.name();
			Thread.sleep(1000);
		}

	}
}