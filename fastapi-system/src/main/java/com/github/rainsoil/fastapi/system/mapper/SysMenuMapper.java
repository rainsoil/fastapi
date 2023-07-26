package com.github.rainsoil.fastapi.system.mapper;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统菜单
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Mapper
public interface SysMenuMapper extends BaseBaseMapper<SysMenu> {
}
