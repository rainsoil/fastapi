package com.github.rainsoil.fastapi.system.mapper;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Mapper
public interface SysUserMapper extends BaseBaseMapper<SysUser> {
}
