package com.github.rainsoil.fastapi.common.core.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 文件名生成 文件md5生成
 *
 * @author luyanan
 * @since 2023/4/1/01
 */
public class SimpleFileNameGenerator implements FileNameGenerator {
    
    
    @Override
    public String generator(byte[] content, String path, String fileName) {
        String ext = FileUtil.extName(fileName);
        return SecureUtil.md5(IoUtil.toStream(content)) + "." + ext;
    }
    
    @Override
    public String pathTransformation(byte[] content, String path, String fileName) {
        return path;
    }
}
