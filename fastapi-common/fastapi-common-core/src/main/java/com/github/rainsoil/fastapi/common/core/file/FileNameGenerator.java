package com.github.rainsoil.fastapi.common.core.file;

/**
 * 文件名生成
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
public interface FileNameGenerator {
    
    
    /**
     * 文件名生成
     *
     * @param content  内容
     * @param path     路径
     * @param fileName 文件名
     * @return java.lang.String
     * @since 2023/4/1/01
     */
    String generator(byte[] content, String path, String fileName);
    
    
    /**
     * 路径转换
     *
     * @param content  文件
     * @param path     路径
     * @param fileName 文件名
     * @return java.lang.String
     * @since 2023/04/03
     */
    String pathTransformation(byte[] content, String path, String fileName);
}
