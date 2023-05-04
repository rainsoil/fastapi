package com.github.rainsoil.fastapi.common.core.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件配置文件
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
@Data
@ConfigurationProperties(prefix = FileProperties.PREFIX)
public class FileProperties {
    
    public static final String PREFIX = "spring.file";
    
    /**
     * 文件的url的域名前缀
     *
     * @since 2023/4/1/01
     */
    private String fileDomain;
    
    /**
     * 是否开启
     *
     * @author luyanan
     * @since 2023/4/1/01
     */
    private Boolean enable;
    
    /**
     * 默认客户端配置
     *
     * @since 2023/4/1/01
     */
    
    private FileClientProperties client;
    
    
    /**
     * 当使用多个客户端的时候用此配置
     *
     * @author luyanan
     * @since 2023/4/1/01
     */
    private Map<String, FileClientProperties> clients = new HashMap<>();
    
    
    /**
     * 获取客户端
     *
     * @return java.util.Map<java.lang.String, com.github.rainsoil.fastapi.common.core.file.FileProperties.FileClientProperties>
     * @since 2023/04/03
     */
    public Map<String, FileClientProperties> getClients() {
        if (!this.clients.containsKey("default") && null != this.client) {
            this.clients.put("default", this.client);
        }
        return clients;
    }
    
    /**
     * 文件客户端配置
     *
     * @author luyanan
     * @since 2023/4/1/01
     */
    @Data
    public static class FileClientProperties {
        
        /**
         * 文件客户端类型
         *
         * @since 2023/4/1/01
         */
        
        private FileClientType type;
        
        /**
         * Region
         *
         * @since 2023/4/1/01
         */
        
        private String endpoint;
        
        /**
         * 客户端id
         *
         * @since 2023/4/1/01
         */
        
        private String accessKeyId;
        
        
        /**
         * 客户端密钥
         *
         * @since 2023/4/1/01
         */
        
        private String accessKeySecret;
        
        /**
         * Bucket 名称
         *
         * @since 2023/4/1/01
         */
        
        private String bucketName;
        
        
        /**
         * 实现类
         *
         * @author luyanan
         * @since 2023/04/01
         */
        private Class<? extends IFileClientService> implClass = FileClientServiceStrategy.class;
        
        /**
         * 本地存储路径, 只有当type为Local的时候,此配置才生效
         *
         * @since 2023/04/03
         */
        
        private String localStoragePath;
    }
    
    
    /**
     * 文件客户端类型
     *
     * @author luyanan
     * @since 2023/4/1/01
     */
    public enum FileClientType {
        /**
         * 本地上传
         *
         * @since 2023/4/1/01
         */
        
        LOCAL,
        /**
         * 对象存储服务器
         *
         * @author luyanan
         * @since 2023/4/1/01
         */
        S3
    }
    
}
