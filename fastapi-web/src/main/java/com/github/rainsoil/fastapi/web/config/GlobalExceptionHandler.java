package com.github.rainsoil.fastapi.web.config;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.exception.BaseException;
import com.github.rainsoil.fastapi.common.core.exception.ErrorException;
import com.github.rainsoil.fastapi.common.core.exception.GlobalMsgCode;
import com.github.rainsoil.fastapi.common.core.exception.WarningException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 全局异常
 *
 * @author luyanan
 * @since 2023/4/4
 **/
@RequiredArgsConstructor
@Slf4j
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {


	private final MessageSource messageSource;


	/**
	 * 参数校验异常
	 *
	 * @param e 异常
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/4/4
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

		ObjectError objectError = allErrors.get(0);
		String defaultMessage = objectError.getDefaultMessage();
		Integer code = GlobalMsgCode.BAD_REQUEST;
		String msg = defaultMessage;
		if (NumberUtil.isNumber(defaultMessage)) {
			code = Integer.valueOf(defaultMessage);
			msg = getMessage(code, e, defaultMessage,null);
		}
		log.error("全局异常信息 code:{},ex={}", code, msg);
		return R.failed(code, msg);
	}

	/**
	 * 参数校验异常
	 *
	 * @param e 异常
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/4/4
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ConstraintViolationException.class)
	public R constraintViolationException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
		StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			ConstraintViolation<?> constraint = iterator.next();
			builder.append(constraint.getMessage());
			break;
		}
		String defaultMessage = builder.toString();
		Integer code = GlobalMsgCode.BAD_REQUEST;
		String msg = defaultMessage;
		if (NumberUtil.isNumber(defaultMessage)) {
			code = Integer.valueOf(defaultMessage);
			msg = getMessage(code, e, defaultMessage, null);
		}
		log.error("全局异常信息 code:{},ex={}", code, msg);
		return R.failed(code, msg);
	}

	/**
	 * 提示异常
	 *
	 * @param e 异常
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/4/4
	 */
	@ExceptionHandler(ErrorException.class)
	@ResponseStatus(HttpStatus.OK)
	public R errrorException(ErrorException e) {
		Integer code = e.getCode();

		String message = getMessage(code, e, null);

		log.error("全局异常信息 code:{},ex={}", code, message, e);
		return R.failed(code, message);
	}

	/**
	 * 错误异常
	 *
	 * @param e 异常
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/4/4
	 */
	@ExceptionHandler(WarningException.class)
	@ResponseStatus(HttpStatus.OK)
	public R warningException(WarningException e) {
		Integer code = e.getCode();

		String message = getMessage(code, e);

		log.warn("全局异常信息 code:{},ex={}", code, message);
		return R.failed(code, message);
	}

	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.OK)
	public R baseException(BaseException e) {
		Integer code = e.getCode();

		String message = getMessage(code, e);

		log.error("全局异常信息 ex={}", message, e);
		return R.failed(code, message);
	}

	/**
	 * AccessDeniedException
	 *
	 * @param e the e
	 * @return R
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(AccessDeniedException.class)
	public R handleAccessDeniedException(AccessDeniedException e) {
		log.error("拒绝授权异常信息 ex={}", e.getMessage());
		return R.failed(HttpStatus.FORBIDDEN.value(), "权限不足，不允许访问");
	}

	/**
	 * validation Exception
	 *
	 * @param exception 异常
	 * @return R
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({BindException.class})
	public R handleBodyValidException(BindException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		// 插入log 的逻辑
		return R.failed(HttpStatus.BAD_REQUEST.value(), fieldErrors.get(0).getDefaultMessage());
	}

	/**
	 * 避免 404 重定向到 /error 导致NPE ,ignore-url 需要配置对应端点
	 *
	 * @return R
	 */
	@DeleteMapping("/error")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public R noHandlerFoundException() {
		return R.failed(HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	/**
	 * 全局异常.
	 *
	 * @param e 异常
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public R handleGlobalException(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return R.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage());
	}

	/**
	 * 获取提示消息
	 *
	 * @param code    消息码
	 * @param e       异常
	 * @param message 异常信息
	 * @param args    参数
	 * @return java.lang.String
	 * @since 2023/4/4
	 */
	private String getMessage(Integer code, Exception e, String message, String... args) {

		String i18nMessage = null;
		try {
			i18nMessage = messageSource.getMessage(code + "", null, LocaleContextHolder.getLocale());
		} catch (NoSuchMessageException noSuchMessageException) {
		}

		if (StrUtil.isNotBlank(i18nMessage) && i18nMessage.matches("\\{\\d\\}")) {
			args = new String[]{message};
		}

		if (StrUtil.isBlank(i18nMessage) && args.length > 0) {
			i18nMessage = args[0];
		}
		if (StrUtil.isBlank(i18nMessage)) {
			i18nMessage = e.getLocalizedMessage();
		}
		if (null != args && args.length > 0) {
			i18nMessage = MessageFormat.format(i18nMessage, args);
		}
		return i18nMessage;

	}


	/**
	 * 获取提示语
	 *
	 * @param code 状态码
	 * @param e    异常
	 * @param args 参数
	 * @return java.lang.String
	 * @since 2023/4/4
	 */
	private String getMessage(Integer code, Exception e, String... args) {
		return getMessage(code, e, e.getMessage(), args);
	}

}
