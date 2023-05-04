package com.github.rainsoil.fastapi.common.core.file;

import java.io.InputStream;

/**
 * 文件服务
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
public interface IFileClientService {
    
    /**
     * 文件上传
     *
     * @param context              文件字节
     * @param path                 文件路径
     * @param fileName             文件名
     * @param fileClientProperties 配置类
     * @return com.github.rainsoil.fastapi.common.core.file.FileInfoVo
     * @since 2023/4/1/01
     */
    FileInfoVo upload(byte[] context, String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties);
    
    
    /**
     * 获取文件流
     *
     * @param path                 文件路径
     * @param fileName             文件名
     * @param fileClientProperties 配置
     * @return java.io.InputStream
     * @since 2023/4/1/01
     */
    InputStream download(String path, String fileName, FileProperties.FileClientProperties fileClientProperties);
}
