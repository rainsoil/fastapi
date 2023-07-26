package com.github.rainsoil.fastapi.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.system.entity.SysRole;
import com.github.rainsoil.fastapi.system.entity.SysUserRole;
import com.github.rainsoil.fastapi.system.mapper.SysRoleMapper;
import com.github.rainsoil.fastapi.system.service.ISysUserRoleService;
import com.github.rainsoil.fastapi.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


	private final ISysUserRoleService sysUserRoleService;

	@Override
	public List<SysRole> listByUserId(Long userId) {
		List<SysUserRole> userRoleList = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
		if (CollectionUtil.isEmpty(userRoleList)) {
			return new ArrayList<>();
		}
		List<Long> roleIds = userRoleList.stream().map(a -> a.getRoleId()).distinct().collect(Collectors.toList());
		return super.listByIds(roleIds);
	}
}
