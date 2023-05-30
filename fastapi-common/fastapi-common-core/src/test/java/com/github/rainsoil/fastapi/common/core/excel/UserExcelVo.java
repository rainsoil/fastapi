package com.github.rainsoil.fastapi.common.core.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户excel 类
 *
 * @author luyanan
 * @since 2023/05/29
 **/
@Accessors(chain = true)
@Data
public class UserExcelVo {


	@ExcelProperty(value = "用户id")
	private Integer id;

	@ExcelProperty(value = "用户名称")
	private String name;

	@ExcelProperty(value = "性别")
	private String sex;
}

