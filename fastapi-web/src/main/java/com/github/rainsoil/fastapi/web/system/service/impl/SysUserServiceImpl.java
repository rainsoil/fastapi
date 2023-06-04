package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.web.system.mapper.SysUserMapper;
import com.github.rainsoil.fastapi.web.system.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户实现类
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
}
