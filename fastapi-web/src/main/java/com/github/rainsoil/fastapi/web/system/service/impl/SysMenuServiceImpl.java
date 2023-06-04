package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.web.system.entity.SysMenu;
import com.github.rainsoil.fastapi.web.system.mapper.SysMenuMapper;
import com.github.rainsoil.fastapi.web.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

/**
 * 系统菜单
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}
