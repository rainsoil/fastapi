package com.github.rainsoil.fastapi.system.service;

import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.system.entity.SysUser;
import com.github.rainsoil.fastapi.system.vo.SysUserVo;

/**
 * 系统用户
 *
 * @author luyanan
 * @since 2023/06/04
 **/
public interface ISysUserService extends IBaseService<SysUser> {

	/**
	 * 根据用户名查询
	 *
	 * @param username 用户名
	 * @return com.github.rainsoil.fastapi.web.system.entity.SysUser
	 * @since 2023/06/09
	 */
	SysUser getByUsername(String username);

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param userId 用户id
	 * @return com.github.rainsoil.fastapi.system.vo.SysUserVo.SysUserInfoVo
	 * @since 2023/06/22
	 */
	SysUserVo.SysUserInfoVo getInfoById(Long userId);
}
