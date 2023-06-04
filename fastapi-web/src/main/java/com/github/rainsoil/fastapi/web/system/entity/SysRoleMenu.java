package com.github.rainsoil.fastapi.web.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统角色菜单关联表
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Accessors(chain = true)
@Data
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * id
	 *
	 * @since 2023/06/04
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 角色id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;


	/**
	 * 菜单id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "菜单id")
	private Long menuId;


}
