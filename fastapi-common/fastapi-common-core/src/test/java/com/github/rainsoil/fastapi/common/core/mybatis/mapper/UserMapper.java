package com.github.rainsoil.fastapi.common.core.mybatis.mapper;

import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import com.github.rainsoil.fastapi.common.core.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 *
 * @author luyanan
 * @since 2023/3/28/028
 */
@Mapper
public interface UserMapper extends BaseBaseMapper<User> {

}
