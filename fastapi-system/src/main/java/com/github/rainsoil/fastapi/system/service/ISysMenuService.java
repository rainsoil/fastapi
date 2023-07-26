package com.github.rainsoil.fastapi.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.github.rainsoil.fastapi.common.core.mybatis.IBaseService;
import com.github.rainsoil.fastapi.system.entity.SysMenu;

import java.util.List;

/**
 * 系统菜单
 *
 * @author luyanan
 * @since 2023/06/04
 */
public interface ISysMenuService extends IBaseService<SysMenu> {

	/**
	 * 根据角色id集合查询菜单
	 *
	 * @param roleIds 角色id集合
	 * @return java.util.List<com.github.rainsoil.fastapi.web.system.entity.SysMenu>
	 * @since 2023/06/09
	 */
	List<SysMenu> listByRoleIds(List<Long> roleIds);

	/**
	 * 菜单过滤
	 *
	 * @param sysMenus 菜单集合
	 * @param type     类型
	 * @param parentId 父菜单id
	 * @return java.util.List<cn.hutool.core.lang.tree.Tree < java.lang.Long>>
	 * @since 2023/06/23
	 */
	List<Tree<Long>> filterMenu(List<SysMenu> sysMenus, String type, Long parentId);
}
