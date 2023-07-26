package com.github.rainsoil.fastapi.system.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户vo类
 *
 * @author luyanan
 * @since 2023/06/22
 **/
public class SysUserVo {


	/**
	 * 系统用户类
	 *
	 * @author luyanan
	 * @since 2023/06/23
	 */
	@Data
	public static class SysUser {
		/**
		 * 创建时间
		 */

		@JsonFormat(pattern = DatePattern.NORM_DATE_PATTERN)
		@ApiModelProperty("创建时间")
		private Date createTime;

		/**
		 * id
		 *
		 * @since 2023/06/04
		 */

		private Long id;
		/**
		 * 登录用户名
		 */
		@ApiModelProperty(value = "登录用户名", notes = "")
		private String userName;
		/**
		 * 手机号
		 */
		@ApiModelProperty(value = "手机号", notes = "")
		private String phone;

		/**
		 * 真实姓名
		 */
		@ApiModelProperty(value = "真实姓名", notes = "")
		private String realName;

		/**
		 * 状态(0:正常,1:冻结)
		 *
		 * @since 2023/06/09
		 */
		@ApiModelProperty(value = "状态(0:正常,1:冻结)")
		private String status;
	}

	/**
	 * 用户详情vo类
	 *
	 * @since 2023/06/22
	 */

	@Data
	@Accessors(chain = true)
	public static class SysUserInfoVo implements Serializable {
		private static final long serialVersionUID = 1L;

		/**
		 * 系统用户
		 *
		 * @since 2023/06/22
		 */
		@ApiModelProperty(value = "用户信息")
		private SysUser sysUser;


		/**
		 * 角色id集合
		 *
		 * @since 2023/06/22
		 */

		@ApiModelProperty(value = "角色id列表")
		private List<Long> roles;


		/**
		 * 角色编码集合
		 *
		 * @since 2023/06/22
		 */
		@ApiModelProperty(value = "角色编码列表")
		private List<String> roleCodes;


		/**
		 * 权限
		 *
		 * @since 2023/06/22
		 */


		@ApiModelProperty(value = "权限列表")
		private List<String> permissions;
	}
}
