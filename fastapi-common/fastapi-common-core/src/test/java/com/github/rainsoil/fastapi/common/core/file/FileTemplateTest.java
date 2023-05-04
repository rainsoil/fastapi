package com.github.rainsoil.fastapi.common.core.file;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FileTemplateTest {
    
    
    @Autowired
    private FileTemplate fileTemplate;
    
    @Test
    public void uploadTest() {
        String tmpPath = System.getProperty("user.dir") + "/tmp";
        FileUtil.mkdir(tmpPath);
        String filePath = tmpPath + "/testupload.csv";
        FileUtil.writeString("123456", filePath, Charset.forName("utf-8"));
        // 上传
        
        FileInfoVo fileInfoVo = fileTemplate.upload(FileUtil.readBytes(filePath), "test", "testupload.csv");
        System.out.println(fileInfoVo);
        FileUtil.del(filePath);
        
    }
}