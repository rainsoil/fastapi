package com.github.rainsoil.fastapi.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.rainsoil.fastapi.core.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Data
@Accessors(chain = true)
public class SysUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 *
	 * @since 2023/06/04
	 */

	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 登录用户名
	 */
	@ApiModelProperty(name = "登录用户名", notes = "")
	private String userName;
	/**
	 * 手机号
	 */
	@ApiModelProperty(name = "手机号", notes = "")
	private String phone;
	/**
	 * 密码
	 */
	@ApiModelProperty(name = "密码", notes = "")
	private String password;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(name = "真实姓名", notes = "")
	private String realName;

	/**
	 * 状态(0:正常,1:冻结)
	 *
	 * @since 2023/06/09
	 */
	@ApiModelProperty(value = "状态(0:正常,1:冻结)")
	private String status;
}
