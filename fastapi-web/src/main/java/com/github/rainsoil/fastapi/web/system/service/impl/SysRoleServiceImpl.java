package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.web.system.entity.SysRole;
import com.github.rainsoil.fastapi.web.system.mapper.SysRoleMapper;
import com.github.rainsoil.fastapi.web.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统角色
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
}
