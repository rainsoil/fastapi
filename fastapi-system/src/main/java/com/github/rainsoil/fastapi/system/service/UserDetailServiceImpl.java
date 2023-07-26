package com.github.rainsoil.fastapi.system.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.rainsoil.fastapi.common.security.core.LoginUser;
import com.github.rainsoil.fastapi.core.enums.GlobalEnums;
import com.github.rainsoil.fastapi.system.entity.SysMenu;
import com.github.rainsoil.fastapi.system.entity.SysRole;
import com.github.rainsoil.fastapi.system.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息查询实现类
 *
 * @author luyanan
 * @since 2023/06/09
 **/
@RequiredArgsConstructor
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {


	private final ISysUserService sysUserService;


	private final ISysRoleService sysRoleService;

	private final ISysMenuService sysMenuService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getByUsername(username);
		if (null == sysUser) {
			log.debug("{}不存在", username);
			throw new UsernameNotFoundException(username + " 不存在");
		}
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(sysUser.getId());
		loginUser.setUsername(sysUser.getUserName());
		loginUser.setEnabled(true);
		loginUser.setPassword(sysUser.getPassword());
		loginUser.setTenantId(sysUser.getTenantId());
		if (GlobalEnums.STATUS_FREEZE.getValue().equals(sysUser.getStatus())) {
			log.debug("{}账号被冻结", username);
			throw new BadCredentialsException(username + "账号被冻结,不允许登录");
		}

		// 查询角色
		List<SysRole> sysRoles = sysRoleService.listByUserId(sysUser.getId());
		loginUser.setRoleCodes(sysRoles.stream().map(a -> a.getRoleCode()).distinct().collect(Collectors.toList()));
		loginUser.setRoleIds(sysRoles.stream().map(a -> a.getId()).distinct().collect(Collectors.toList()));
		// 查询菜单
		if (CollectionUtil.isNotEmpty(sysRoles)) {
			List<SysMenu> sysMenus = sysMenuService.listByRoleIds(sysRoles.stream().map(a -> a.getId()).collect(Collectors.toList()));
			loginUser.setAuths(sysMenus.stream().filter(a -> StrUtil.isBlank(a.getPermission())).map(a -> a.getPermission()).distinct().collect(Collectors.toList()));
		}
		return loginUser;
	}

}
