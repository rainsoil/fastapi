package com.github.rainsoil.fastapi.common.core.excel;

import cn.hutool.core.collection.CollectionUtil;
import com.github.rainsoil.fastapi.common.core.excel.annotation.ResponseExcel;
import com.github.rainsoil.fastapi.common.core.excel.annotation.Sheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * excel 控制层
 *
 * @author luyanan
 * @since 2023/05/29
 **/
@RestController
@RequestMapping("excel")
public class ExcelTestController {


	@ResponseExcel(name = "user", sheets = {
			@Sheet(headClazz = UserExcelVo.class)
	})
	@GetMapping("export")
	public List<List<UserExcelVo>> export() {
		List<List<UserExcelVo>> res = new ArrayList<>();
		res.add(createTestUser());
		return res;
	}

	/**
	 * 创建测试用户
	 *
	 * @return java.util.List<com.github.rainsoil.fastapi.common.core.excel.UserExcelVo>
	 * @since 2023/05/29
	 */
	private List<UserExcelVo> createTestUser() {
		List<UserExcelVo> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			UserExcelVo userExcelVo = new UserExcelVo()
					.setId(i)
					.setName("user-" + i)
					.setSex((i % 2 == 0) ? "1" : "0");
			list.add(userExcelVo);
		}
		return list;
	}

}
