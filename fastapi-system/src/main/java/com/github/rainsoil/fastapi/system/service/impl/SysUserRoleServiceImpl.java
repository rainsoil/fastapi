package com.github.rainsoil.fastapi.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.system.entity.SysUserRole;
import com.github.rainsoil.fastapi.system.mapper.SysUserRoleMapper;
import com.github.rainsoil.fastapi.system.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author luyanan
 * @since 2023/06/04
 **/
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
}
