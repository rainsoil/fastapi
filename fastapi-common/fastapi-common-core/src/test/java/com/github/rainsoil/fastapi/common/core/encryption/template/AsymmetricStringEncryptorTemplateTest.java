package com.github.rainsoil.fastapi.common.core.encryption.template;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AsymmetricStringEncryptorTemplateTest {

	@Autowired
	private AsymmetricStringEncryptorTemplate asymmetricStringEncryptorTemplate;

	@Test
	public void test() {

		String message = "123456";
		String encrypt = asymmetricStringEncryptorTemplate.encrypt(message);
		String decrypt = asymmetricStringEncryptorTemplate.decrypt(encrypt);
		assertEquals(message, decrypt);
	}


}