package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.web.system.entity.SysUserRole;
import com.github.rainsoil.fastapi.web.system.mapper.SysUserRoleMapper;
import com.github.rainsoil.fastapi.web.system.service.ISysUserRoleService;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luyanan
 * @since 2023/06/04
 **/
@Mapper
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
}
