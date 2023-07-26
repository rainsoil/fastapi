package com.github.rainsoil.fastapi.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.core.core.BaseController;
import com.github.rainsoil.fastapi.system.entity.SysMenu;
import com.github.rainsoil.fastapi.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单模块
 *
 * @author luyanan
 * @since 2023/06/23
 **/
@Api(tags = "系统菜单")
@RequiredArgsConstructor
@RequestMapping("menu")
@RestController
public class SysMenuController extends BaseController {


	private final ISysMenuService sysMenuService;

	/**
	 * 获取当前用户的树形菜单集合
	 *
	 * @param type     类型
	 * @param parentId 父节点id
	 * @return com.github.rainsoil.fastapi.common.core.R<java.util.List < cn.hutool.core.lang.tree.Tree < java.lang.Long>>>
	 * @since 2023/06/23
	 */
	@GetMapping()
	@ApiOperation(value = "获取当前用户的树形菜单集合")
	public R<List<Tree<Long>>> getUserMenu(String type, Long parentId) {
		// 获取符合条件的菜单
		List<SysMenu> sysMenus = sysMenuService.listByRoleIds(getUser().getRoleIds());
		return R.ok(sysMenuService.filterMenu(sysMenus, type, parentId));
	}
}
