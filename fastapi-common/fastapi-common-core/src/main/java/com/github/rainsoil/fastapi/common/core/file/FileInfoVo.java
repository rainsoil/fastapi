package com.github.rainsoil.fastapi.common.core.file;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件信息
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
@Data
@Accessors(chain = true)
public class FileInfoVo {
    
    /**
     * 相对路径
     *
     * @since 2023/4/1/01
     */
    
    private String relativePath;
    
    
    /**
     * 文件的全路径
     *
     * @since 2023/4/1/01
     */
    
    private String url;
    
    /**
     * 文件名
     *
     * @since 2023/4/1/01
     */
    
    private String name;
}
