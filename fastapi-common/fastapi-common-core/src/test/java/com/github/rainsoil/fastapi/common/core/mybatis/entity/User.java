package com.github.rainsoil.fastapi.common.core.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {
    
    /**
     * id
     *
     * @since 2023/3/28/028
     */
    
    private Long id;
    
    /**
     * 名称
     *
     * @since 2023/3/28/028
     */
    
    private String name;
    
    
    /**
     * 年龄
     *
     * @since 2023/3/28/028
     */
    
    private Integer age;
    
    
    /**
     * 邮箱
     *
     * @since 2023/3/28/028
     */
    
    private String email;
}
