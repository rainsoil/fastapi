package com.github.rainsoil.fastapi.common.core.file;


import com.github.rainsoil.fastapi.common.core.spring.SpringContextHolder;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

/**
 * 文件服务策略
 *
 * @author luyanan
 * @since 2023/04/01
 */
@RequiredArgsConstructor
public class FileClientServiceStrategy implements IFileClientService {
    
    private final FileNameGenerator fileNameGenerator;
    
    @Override
    public FileInfoVo upload(byte[] context, String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties) {
        path = fileNameGenerator.pathTransformation(context, path, fileName);
        fileName = fileNameGenerator.generator(context, path, fileName);
        return getFileClientService(context, path, fileName, fileClientProperties).upload(context, path, fileName,
                fileClientProperties);
    }
    
    /**
     * 获取文件客户端服务
     *
     * @param context              文件字节
     * @param path                 路径
     * @param fileName             文件名
     * @param fileClientProperties 客户端配置文件
     * @return com.github.rainsoil.fastapi.common.core.file.IFileClientService
     * @since 2023/04/03
     */
    protected IFileClientService getFileClientService(byte[] context, String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties) {
        
        Optional<String> first = Arrays.stream(
                        SpringContextHolder.getApplicationContext().getBeanNamesForType(IFileClientService.class))
                .filter(a -> a.equals(fileClientProperties.getType().name())).findFirst();
        
        String s = first.get();
        IFileClientService fileClientService = SpringContextHolder.getApplicationContext()
                .getBean(s, IFileClientService.class);
        return fileClientService;
    }
    
    @Override
    public InputStream download(String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties) {
        path = fileNameGenerator.pathTransformation(null, path, fileName);
        fileName = fileNameGenerator.generator(null, path, fileName);
        return getFileClientService(null, path, fileName, fileClientProperties).download(path, fileName,
                fileClientProperties);
    }
}
