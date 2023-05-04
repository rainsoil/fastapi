package com.github.rainsoil.fastapi.common.core.file;

import cn.hutool.core.io.FileUtil;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.InputStream;

/**
 * 文件本地存储
 *
 * @author luyanan
 * @since 2023/04/03
 */
@RequiredArgsConstructor
public class LocalFileClientService implements IFileClientService {
    
    private final FileProperties fileProperties;
    
    @Override
    public FileInfoVo upload(byte[] context, String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties) {
        String localStoragePath = fileClientProperties.getLocalStoragePath();
        
        String fullPath = localStoragePath + "/" + path + "/" + fileName;
        FileUtil.writeBytes(context, new File(fullPath));
        String relativePath = path + "/" + fileName;
        return new FileInfoVo().setRelativePath(relativePath).setName(fileName)
                .setUrl(fileProperties.getFileDomain() + "/" + relativePath);
        
    }
    
    @Override
    public InputStream download(String path, String fileName,
            FileProperties.FileClientProperties fileClientProperties) {
        String localStoragePath = fileClientProperties.getLocalStoragePath();
        String fullPath = localStoragePath + "/" + path + "/" + fileName;
        return FileUtil.getInputStream(fullPath);
    }
}
