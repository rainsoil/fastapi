package com.github.rainsoil.fastapi.web.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户角色关联表
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Data
@Accessors(chain = true)
public class SysUserRole {


	/**
	 * id
	 *
	 * @since 2023/06/04
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 用户id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;


	/**
	 * 角色id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;
}
