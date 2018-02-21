package org.zdesk.models;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
	
	USER,
	ADMIN,
	FACEBOOK_USER;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getAuthority();
	}
}