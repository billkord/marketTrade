package com.market.trade.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
	/**
	 *
	 */
	private static final long serialVersionUID = -8746878804886516134L;
    private static boolean accountNonExpired = true;
    private static boolean enabled = true;
	private static boolean credentialsNonExpired = true;
	private static boolean accountNonLocked = true;
	private Long id;

	public CustomUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}