package com.github.rainsoil.fastapi.core.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author luyanan
 * @since 2023/04/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    
    /**
     * 租户号
     */
    
    @ApiModelProperty("租户号")
    @TableField("tenant_id")
    private Long tenantId;
    
    /**
     * 创建人
     */
    
    @ApiModelProperty("创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;
    
    /**
     * 创建时间
     */
    
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    
    /**
     * 更新人
     */
    
    @ApiModelProperty("更新人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    
    /**
     * 更新时间
     */
    @OrderBy
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    /**
     * 删除标识
     */
    
    @ApiModelProperty("删除标识")
    @TableField("del_flag")
    @TableLogic
    private String delFlag;
}
