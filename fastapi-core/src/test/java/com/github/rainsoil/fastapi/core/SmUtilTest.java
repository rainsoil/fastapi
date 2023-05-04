package com.github.rainsoil.fastapi.core;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * smUtils测试
 *
 * @author luyanan
 * @since 2023/4/4
 **/
public class SmUtilTest {


	@Test
	public void sm4() {
		String key = "pjh1ximwfz3o9lzk";
		SM4 sm4 = SmUtil.sm4(key.getBytes(StandardCharsets.UTF_8));
		String content = "我只是个加密串";
		String encryptHex = sm4.encryptHex(content);
		String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
		System.out.println(encryptHex);
		System.out.println(decryptStr);
	}
}
