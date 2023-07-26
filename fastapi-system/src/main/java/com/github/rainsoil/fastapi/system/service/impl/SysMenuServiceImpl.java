package com.github.rainsoil.fastapi.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import com.github.rainsoil.fastapi.system.enums.SysMenuEnums;
import com.github.rainsoil.fastapi.system.service.ISysRoleMenuService;
import com.github.rainsoil.fastapi.system.entity.SysMenu;
import com.github.rainsoil.fastapi.system.entity.SysRoleMenu;
import com.github.rainsoil.fastapi.system.mapper.SysMenuMapper;
import com.github.rainsoil.fastapi.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 系统菜单
 *
 * @author luyanan
 * @since 2023/06/04
 **/
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	private final ISysRoleMenuService sysRoleMenuService;

	@Override
	public List<SysMenu> listByRoleIds(List<Long> roleIds) {
		return Opt.ofEmptyAble(roleIds).map(rids -> {
			List<SysRoleMenu> list = sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, rids));
			if (CollectionUtil.isEmpty(list)) {
				return new ArrayList<SysMenu>();
			}
			return this.listByIds(list.stream().map(a -> a.getMenuId()).distinct().collect(Collectors.toList()));
		}).orElse(new ArrayList<SysMenu>());

	}

	@Override
	public List<Tree<Long>> filterMenu(List<SysMenu> sysMenus, String type, Long parentId) {
		List<TreeNode<Long>> collect = sysMenus.stream().filter(menuTypePredicate(type)).map(getNodeFunction())
				.collect(Collectors.toList());
		Long parent = parentId == null ? Long.valueOf(SysMenuEnums.ROOT_ID.getValue()) : parentId;
		return TreeUtil.build(collect, parent);
	}

	@NotNull
	private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
		return menu -> {
			TreeNode<Long> node = new TreeNode<>();
			node.setId(menu.getId());
			node.setName(menu.getName());
			node.setParentId(menu.getParentId());
			node.setWeight(menu.getSort());
			// 扩展属性
			Map<String, Object> extra = new HashMap<>(8);
			extra.put("icon", menu.getIcon());
			extra.put("path", menu.getPath());
			extra.put("type", menu.getType());
			extra.put("permission", menu.getPermission());
			extra.put("label", menu.getName());
			extra.put("sort", menu.getSort());
			extra.put("keepAlive", menu.getKeepAlive());
			extra.put("blank", menu.getBlank());
			node.setExtra(extra);
			return node;
		};
	}


	/**
	 * menu类型断言
	 *
	 * @param type 类型
	 * @return java.util.function.Predicate<com.github.rainsoil.fastapi.system.entity.SysMenu>
	 * @since 2023/06/23
	 */
	private Predicate<SysMenu> menuTypePredicate(String type) {
		return menu -> {
			if (SysMenuEnums.MENU_TYPE_TOP.getValue().equals(type)) {
				return SysMenuEnums.MENU_TYPE_TOP.getType().equals(menu.getType());
			} else {
				return !SysMenuEnums.MENU_TYPE_BUTTON.getType().equals(menu.getType());
			}

		};
	}

}
