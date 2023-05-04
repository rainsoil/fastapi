package com.github.rainsoil.fastapi.common.core.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.github.yulichang.injector.MPJSqlInjector;

import java.util.List;

/**
 * SQL 方法注入扩展
 *
 * @author luyanan
 * @since 2023/3/28
 **/
public class MySqlInjector extends MPJSqlInjector {
    
    
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
    
    
}
