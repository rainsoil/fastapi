package com.github.rainsoil.fastapi.common.core.validation;

import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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


}
