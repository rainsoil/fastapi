package com.github.rainsoil.fastapi.common.core.validation;

import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 对象属性校验
 *
 * @author luyanan
 * @since 2023/3/28
 **/
@UtilityClass
public class BeanValidators {
	public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * 对象属性校验
	 *
	 * @param validator validator
	 * @param object    对象
	 * @param groups    分组
	 * @since 2023/3/28
	 */
	public void validate(Validator validator, Object object, Class<?>... groups) throws ConstraintViolationException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	/**
	 * 校验
	 *
	 * @param <T>    校验实体
	 * @param target 实体类
	 * @return java.util.Set<javax.validation.ConstraintViolation < T>>
	 * @since 2023/05/28
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T target) {
		return VALIDATOR.validate(target);
	}
}
