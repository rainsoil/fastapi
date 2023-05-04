package com.github.rainsoil.fastapi.common.core.file;

import cn.hutool.core.io.IoUtil;
import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件上传模板类
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
@RequiredArgsConstructor
public class FileTemplate {
    
    private final FileProperties fileProperties;
    
    
    private String name = "default";
    
    /**
     * 获取客户端配置文件
     *
     * @param name 名称
     * @return com.github.rainsoil.fastapi.common.core.file.FileProperties.FileClientProperties
     * @since 2023/04/03
     */
    public FileProperties.FileClientProperties getFileClientProperties(String name) {
        
        return fileProperties.getClients().get(name);
    }
    
    /**
     * 切换客户端
     *
     * @param name 客户端名称
     * @since 2023/04/01
     */
    public void switcher(String name) {
        this.name = name;
    }
    
    /**
     * 文件上传
     *
     * @param context  文件字节
     * @param path     文件路径
     * @param fileName 文件名
     * @return com.github.rainsoil.fastapi.common.core.file.FileInfoVo
     * @since 2023/4/1/01
     */
    public FileInfoVo upload(byte[] context, String path, String fileName) {
        
        FileProperties.FileClientProperties fileClientProperties = getFileClientProperties(name);
        Class<? extends IFileClientService> implClass = fileClientProperties.getImplClass();
        IFileClientService fileClientService = SpringContextHolder.getBean(implClass);
        return fileClientService.upload(context, path, fileName, fileClientProperties);
    }
    
    /**
     * 文件上传
     *
     * @param inputStream 文件流
     * @param path        文件路径
     * @param fileName    文件名
     * @return com.github.rainsoil.fastapi.common.core.file.FileInfoVo
     * @since 2023/4/1/01
     */
    public FileInfoVo upload(InputStream inputStream, String path, String fileName) {
        byte[] bytes = IoUtil.readBytes(inputStream);
        return upload(bytes, path, fileName);
    }
    
    
    /**
     * 文件上传
     *
     * @param file 文件
     * @param path 文件路径
     * @return com.github.rainsoil.fastapi.common.core.file.FileInfoVo
     * @since 2023/4/1/01
     */
    @SneakyThrows
    public FileInfoVo upload(MultipartFile file, String path) {
        InputStream inputStream = file.getInputStream();
        return upload(inputStream, path, file.getOriginalFilename());
    }
    
    /**
     * 获取文件流
     *
     * @param path     文件路径
     * @param fileName 文件名
     * @return java.io.InputStream
     * @since 2023/4/1/01
     */
    public InputStream download(String path, String fileName) {
        FileProperties.FileClientProperties fileClientProperties = getFileClientProperties(name);
        Class<? extends IFileClientService> implClass = fileClientProperties.getImplClass();
        IFileClientService fileClientService = SpringContextHolder.getBean(implClass);
        return fileClientService.download(path, fileName, fileClientProperties);
    }
}
