package com.github.rainsoil.fastapi.system.service;

import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.system.entity.SysRole;

import java.util.List;

/**
 * 系统角色
 *
 * @author luyanan
 * @since 2023/06/04
 **/
public interface ISysRoleService extends IBaseService<SysRole> {

	/**
	 * 根据用户id查询角色列表
	 *
	 * @param userId 用户id
	 * @return java.util.List<com.github.rainsoil.fastapi.web.system.entity.SysRole>
	 * @since 2023/06/09
	 */
	List<SysRole> listByUserId(Long userId);
}
