package com.github.rainsoil.fastapi.core.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 自定义字段填充
 *
 * @author luyanan
 * @since 2023/04/03
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
    
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
    
    }
}
