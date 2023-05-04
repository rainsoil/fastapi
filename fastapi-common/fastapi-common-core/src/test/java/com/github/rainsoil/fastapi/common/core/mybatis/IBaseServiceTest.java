package com.github.rainsoil.fastapi.common.core.mybatis;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.mybatis.entity.User;
import com.github.rainsoil.fastapi.common.core.mybatis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class IBaseServiceTest {
    
    @Autowired
    private IUserService userService;
    
    
    @Test
    public void page() {
        
        PageRequest<User> pageRequest = new PageRequest<>();
        pageRequest.setCurrent(1L);
        pageRequest.setSize(100);
        PageInfo<User> page = userService.page(pageRequest);
        log.debug(page.getRecords().toString());
    }
    
}