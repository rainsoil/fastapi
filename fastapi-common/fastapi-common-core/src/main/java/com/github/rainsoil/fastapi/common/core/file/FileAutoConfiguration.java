package com.github.rainsoil.fastapi.common.core.file;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件服务自动化配置类
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
@Configuration
@EnableConfigurationProperties(FileProperties.class)
@ConditionalOnProperty(prefix = FileProperties.PREFIX, value = "enable", havingValue = "true", matchIfMissing = false)
public class FileAutoConfiguration {
    
    
    
    /**
     * 文件名生成类
     *
     * @return com.github.rainsoil.fastapi.common.core.file.FileNameGenerator
     * @since 2023/4/1/01
     */
    @ConditionalOnMissingBean
    @Bean
    public FileNameGenerator fileNameGenerator() {
        return new SimpleFileNameGenerator();
    }
    
    /**
     * 本地存储
     *
     * @param fileProperties 文件配置文件
     * @return com.github.rainsoil.fastapi.common.core.file.IFileClientService
     * @since 2023/04/03
     */
    @Bean("LOCAL")
    public IFileClientService local(FileProperties fileProperties) {
        return new LocalFileClientService(fileProperties);
    }
    
    
    /**
     * 文件存储策略
     *
     * @param fileNameGenerator 文件命名模块
     * @return com.github.rainsoil.fastapi.common.core.file.IFileClientService
     * @since 2023/04/03
     */
    @Bean
    public IFileClientService fileClientServiceStrategy(FileNameGenerator fileNameGenerator) {
        return new FileClientServiceStrategy(fileNameGenerator);
    }
    
    /**
     * 文件操作模板
     *
     * @param fileProperties 文件配置文件
     * @return com.github.rainsoil.fastapi.common.core.file.FileTemplate
     * @since 2023/04/03
     */
    @Bean
    public FileTemplate fileTemplate(FileProperties fileProperties) {
        return new FileTemplate(fileProperties);
    }
}
