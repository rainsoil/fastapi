package com.github.rainsoil.fastapi.common.core.logger;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * 日志测试
 *
 * @author luyanan
 * @since 2023/05/22
 **/
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class LoggerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@LocalServerPort
	private int rdmServerPort;

	@Autowired
	private MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}


	@Test
	public void test() throws Exception {
		// 添加调用的接口路径
		MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.get("/test");
		// 标识数据提交方式
		postRequestBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		postRequestBuilder.param("name", "1111");
		MvcResult mvcResult = mockMvc.perform(postRequestBuilder).andReturn();

	}


}
