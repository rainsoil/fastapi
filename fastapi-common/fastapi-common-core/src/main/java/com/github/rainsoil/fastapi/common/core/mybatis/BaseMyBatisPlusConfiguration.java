package com.github.rainsoil.fastapi.common.core.mybatis;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 配置类
 *
 * @author luyanan
 * @since 2023/3/28/028
 */
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
@EnableConfigurationProperties({BaseMybatisPlusProperties.class})
@Configuration
public class BaseMyBatisPlusConfiguration {
    
    
    /**
     * sql注入器方法扩展
     *
     * @return com.github.rainsoil.fastapi.common.core.mybatis.MySqlInjector
     * @since 2023/3/28/028
     */
    @Bean
    public MySqlInjector blocksSqlInjector() {
        return new MySqlInjector();
    }
    
    
    /**
     * mybatis plus 插件配置
     *
     * @param baseMybatisPlusProperties 配置
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @since 2023/3/28/028
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(BaseMybatisPlusProperties baseMybatisPlusProperties) {
        
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        
        BaseMybatisPlusProperties.Plugins plugins = baseMybatisPlusProperties.getPlugins();
        
        if (null != plugins) {
            // 分页插件
            BaseMybatisPlusProperties.Plugins.Pagination pagination = plugins.getPagination();
            if (null != pagination && pagination.isEnable()) {
                PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
                paginationInnerInterceptor.setDbType(pagination.getDbType());
                paginationInnerInterceptor.setMaxLimit(pagination.getMaxLimit());
                mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
            }
            
            // 阻断插件
            if (plugins.isBlockAttack()) {
                BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
                mybatisPlusInterceptor.addInnerInterceptor(blockAttackInnerInterceptor);
            }
            // 乐观锁
            if (plugins.isOptimisticLocker()) {
                OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
                mybatisPlusInterceptor.addInnerInterceptor(optimisticLockerInnerInterceptor);
            }
        }
        return mybatisPlusInterceptor;
    }
    
    /**
     * 自定义id主键策略
     *
     * @return com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator
     * @since 2023/04/03
     */
    @ConditionalOnClass(YitIdHelper.class)
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new CustomKeyGenerator();
    }
}
