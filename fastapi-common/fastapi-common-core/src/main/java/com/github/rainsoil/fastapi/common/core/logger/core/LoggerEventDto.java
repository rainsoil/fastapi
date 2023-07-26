package com.github.rainsoil.fastapi.common.core.logger.core;

import lombok.Data;
import lombok.experimental.Accessors;
import org.aspectj.lang.Signature;

import java.io.Serializable;

/**
 * 日志事件传输对象
 *
 * @author luyanan
 * @since 2023/05/21
 **/
@Data
@Accessors(chain = true)
public class LoggerEventDto implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 类型
	 *
	 * @since 2023/06/06
	 */

	private String type;
	/**
	 * 返回结果
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Object result;


	/**
	 * 请求开始时间
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Long startTime;


	/**
	 * 请求结束时间
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Long endTime;


	/**
	 * 异常
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Throwable throwable;


	/**
	 * 方法签名
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Signature signature;


	/**
	 * 方法参数
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private Object[] args;


	/**
	 * 链路id
	 *
	 * @author luyanan
	 * @since 2023/05/21
	 */
	private String traceId;
}
