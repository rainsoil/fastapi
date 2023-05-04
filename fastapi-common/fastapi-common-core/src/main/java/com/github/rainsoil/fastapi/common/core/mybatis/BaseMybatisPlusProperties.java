package com.github.rainsoil.fastapi.common.core.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mybatis plus的配置
 *
 * @author luyanan
 * @since 2023/3/28/028
 */
@ConfigurationProperties(prefix = "mybatis-plus")
@Data
public class BaseMybatisPlusProperties {
    
    /**
     * 插件
     *
     * @author luyanan
     * @since 2023/3/28/028
     */
    private Plugins plugins = new Plugins();
    
    /**
     * 插件
     *
     * @author luyanan
     * @since 2023/3/28/028
     */
    @Data
    public static class Plugins {
        
        /**
         * 分页插件
         *
         * @author luyanan
         * @since 2023/3/28/028
         */
        private Pagination pagination = new Pagination();
        
        /**
         * 乐观锁插件
         *
         * @since 2023/3/28/028
         */
        
        private boolean optimisticLocker = false;
        
        
        /**
         * 全表删除或者更新阻断
         *
         * @since 2023/3/28/028
         */
        
        private boolean blockAttack = false;
        
        /**
         * 分页的配置
         *
         * @author luyanan
         * @since 2023/3/28/028
         */
        @Data
        public static class Pagination {
            
            /**
             * 是否开启
             *
             * @since 2023/3/28/028
             */
            
            private boolean enable = true;
            
            
            /**
             * 数据库类型
             *
             * @since 2023/3/28/028
             */
            
            private DbType dbType = DbType.MYSQL;
            
            /**
             * 最大限制跳数
             *
             * @since 2023/3/28/028
             */
            
            private long maxLimit = -1;
        }
    }
}
