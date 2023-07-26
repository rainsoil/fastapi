package com.github.rainsoil.fastapi.system.entity;

import com.github.rainsoil.fastapi.core.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统菜单表
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Data
@Accessors(chain = true)
public class SysMenu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 菜单id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "菜单id")
	private Long id;


	/**
	 * 菜单名称
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	/**
	 * 系统权限
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "系统权限")
	private String permission;

	/**
	 * 菜单路径
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "菜单路径")
	private String path;


	/**
	 * 父id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "父id")
	private Long parentId;

	/**
	 * icon
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "icon")
	private String icon;

	/**
	 * 排序
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;


	/**
	 * 保持活跃(1:不,0:保持)
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "保持活跃(1:不,0:保持)")
	private String keepAlive;

	/**
	 * 菜单类型(1:菜单,2:按钮)
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "菜单类型(1:菜单,2:按钮)")
	private String type;

	/**
	 * 是否外链(1:否,0:是)
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "是否外链(1:否,0:是)")
	private String blank;
}
