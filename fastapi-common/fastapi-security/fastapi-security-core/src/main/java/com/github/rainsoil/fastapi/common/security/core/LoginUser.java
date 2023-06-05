package com.github.rainsoil.fastapi.common.security.core;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录用户
 *
 * @author luyanan
 * @since 2023/06/05
 */
@Data
public class LoginUser implements UserDetails {

	/**
	 * 用户id
	 *
	 * @since 2023/06/05
	 */

	private Long userId;


	/**
	 * 用户名
	 *
	 * @since 2023/06/05
	 */

	private String username;


	/**
	 * 密码
	 *
	 * @since 2023/06/05
	 */

	private String password;


	/**
	 * 角色编码
	 *
	 * @since 2023/06/05
	 */

	private List<String> roleCodes;

	/**
	 * 授权
	 *
	 * @since 2023/06/05
	 */

	private List<String> auths;


	/**
	 * 租户id
	 *
	 * @since 2023/06/05
	 */

	private String tenantId;


	/**
	 * 真实姓名
	 *
	 * @since 2023/06/05
	 */

	private String relName;
	/**
	 * 补充数据
	 *
	 * @since 2023/06/05
	 */

	private Map<String, Object> ext = new HashMap<>();

	/**
	 * 是否开启
	 *
	 * @since 2023/06/05
	 */

	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (CollectionUtils.isEmpty(this.auths)) {
			return null;
		}
		return AuthorityUtils.createAuthorityList(this.auths.stream().collect(Collectors.joining(",")));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


}
