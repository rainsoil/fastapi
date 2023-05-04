package com.github.rainsoil.fastapi.common.core.mybatis.service.impl;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.common.core.mybatis.entity.User;
import com.github.rainsoil.fastapi.common.core.mybatis.mapper.UserMapper;
import com.github.rainsoil.fastapi.common.core.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 实现
 *
 * @author luyanan
 * @since 2023/3/28/028
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

}
