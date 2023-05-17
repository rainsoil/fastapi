package com.github.rainsoil.fastapi.common.core.encryption.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SymmetricStringEncryptorTemplateTest {

	@Autowired
	private SymmetricStringEncryptorTemplate symmetricStringEncryptorTemplate;

	@Test
	public void test() {

		String str = "12ab3456789udhty";
		String encrypt = symmetricStringEncryptorTemplate.encrypt(str);
		String decrypt = symmetricStringEncryptorTemplate.decrypt(encrypt);
		assertEquals(str,decrypt);
	}
}