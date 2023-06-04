package com.github.rainsoil.fastapi.web.system.mapper;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.web.system.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统角色菜单关联表
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Mapper
public interface SysRoleMenuMapper extends BaseBaseMapper<SysRoleMenu> {
}
