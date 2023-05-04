package com.github.rainsoil.fastapi.core.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 参数校验配置
 *
 * @author luyanan
 * @since 2023/04/03
 */
@Configuration
public class ValidatorConfig {


	/**
	 * 参数校验配置
	 *
	 * @return javax.validation.Validator
	 * @since 2023/4/4
	 */
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
				.buildValidatorFactory();
		return validatorFactory.getValidator();
	}
}
