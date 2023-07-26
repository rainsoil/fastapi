package com.github.rainsoil.fastapi.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.system.entity.SysMenu;
import com.github.rainsoil.fastapi.system.entity.SysRole;
import com.github.rainsoil.fastapi.system.entity.SysUser;
import com.github.rainsoil.fastapi.system.mapper.SysUserMapper;
import com.github.rainsoil.fastapi.system.service.ISysMenuService;
import com.github.rainsoil.fastapi.system.service.ISysRoleService;
import com.github.rainsoil.fastapi.system.service.ISysUserService;
import com.github.rainsoil.fastapi.system.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户实现类
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@RequiredArgsConstructor
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


	private final ISysRoleService sysRoleService;

	private final ISysMenuService sysMenuService;

	@Override
	@Cacheable(cacheNames = "getByUsername#1d")
	public SysUser getByUsername(String username) {
		if (StrUtil.isBlank(username)) {
			return null;
		}
		return super.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username));
	}

	@Override
	public SysUserVo.SysUserInfoVo getInfoById(Long userId) {

		SysUser sysUser = this.getById(userId);
		if (null == sysUser) {
			return null;
		}

		SysUserVo.SysUserInfoVo userInfoVo = new SysUserVo.SysUserInfoVo();
		SysUserVo.SysUser sysUserVo = BeanConvertUtils.convertTo(sysUser, SysUserVo.SysUser::new);
		userInfoVo.setSysUser(sysUserVo);
		List<SysRole> sysRoles = sysRoleService.listByUserId(userId);
		userInfoVo.setRoles(sysRoles.stream().map(a -> a.getId()).distinct().collect(Collectors.toList()));
		userInfoVo.setRoleCodes(sysRoles.stream().map(a -> a.getRoleCode()).distinct().collect(Collectors.toList()));
		List<SysMenu> sysMenus = sysMenuService.listByRoleIds(sysRoles.stream().map(a -> a.getId()).distinct().collect(Collectors.toList()));
		userInfoVo.setPermissions(sysMenus.stream().filter(a -> StrUtil.isNotBlank(a.getPermission())).map(a -> a.getPermission()).distinct().collect(Collectors.toList()));
		return userInfoVo;
	}
}
