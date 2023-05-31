package com.github.rainsoil.fastapi.common.core.excel.conver;

import com.alibaba.excel.converters.Converter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 全局转换器注册
 *
 * @author luyanan
 * @since 2023/05/31
 **/
@Getter
@Slf4j
public class GlobalConverterRegister {

	@Autowired
	private ObjectProvider<Converter> converters;


}
