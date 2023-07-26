package com.github.rainsoil.fastapi.system.controller;

import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.core.core.BaseController;
import com.github.rainsoil.fastapi.system.service.ISysUserService;
import com.github.rainsoil.fastapi.system.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户
 *
 * @author luyanan
 * @since 2023/06/14
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("admin/user")
@RequiredArgsConstructor
public class SysUserController extends BaseController {


	private final ISysUserService sysUserService;


	/**
	 * 获取登录当前用户信息
	 *
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.system.vo.SysUserVo.SysUserInfoVo>
	 * @since 2023/06/22
	 */
	@ApiOperation(value = "获取登录当前用户信息")
	@GetMapping("info")
	public R<SysUserVo.SysUserInfoVo> info() {
		SysUserVo.SysUserInfoVo userInfoVo = sysUserService.getInfoById(getUserId());
		return R.ok(userInfoVo);
	}


}



