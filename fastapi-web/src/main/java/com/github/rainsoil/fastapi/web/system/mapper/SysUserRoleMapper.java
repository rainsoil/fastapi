package com.github.rainsoil.fastapi.web.system.mapper;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.web.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户角色
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Mapper
public interface SysUserRoleMapper extends BaseBaseMapper<SysUserRole> {
}
