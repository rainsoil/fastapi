package com.github.rainsoil.fastapi.web.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.rainsoil.fastapi.core.core.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统角色表
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Data
@Accessors(chain = true)
public class SysRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * id
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = " id")
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;


	/**
	 * 角色编码
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "角色编码")
	private String roleCode;


	/**
	 * 角色名称
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = " 角色名称")
	private String roleName;

	/**
	 * 备注
	 *
	 * @since 2023/06/04
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
}
