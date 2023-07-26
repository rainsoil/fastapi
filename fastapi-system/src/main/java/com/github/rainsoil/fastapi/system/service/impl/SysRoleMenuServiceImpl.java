package com.github.rainsoil.fastapi.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.system.entity.SysRoleMenu;
import com.github.rainsoil.fastapi.system.mapper.SysRoleMenuMapper;
import com.github.rainsoil.fastapi.system.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author luyanan
 * @since 2023/06/04
 **/
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
}
