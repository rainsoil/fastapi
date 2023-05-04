package com.github.rainsoil.fastapi.common.core.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.idgen.YitIdHelper;

/**
 * 自定义id生成策略
 *
 * @author luyanan
 * @since 2023/04/03
 */
public class CustomKeyGenerator implements IdentifierGenerator {
    
    @Override
    public Number nextId(Object entity) {
        return YitIdHelper.nextId();
    }
}
