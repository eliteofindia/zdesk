package org.zdesk.utils;

import java.security.Principal;
import java.util.Collection;

import org.zdesk.models.*;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class OAuth2AuthenticationToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = -2952756871952641628L;
	
	private CustomPrincipal principal;

	public OAuth2AuthenticationToken(Object user, Collection<? extends GrantedAuthority> arg0) {
		super(arg0);
		this.setPrincipal(user);
		System.out.println("############## USER DETAILS ####################");
		System.out.println(((Principal)user));
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return ((Authentication)(Principal)this.getPrincipal()).getCredentials();
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}
	
	public void setPrincipal(Object user) {
		
		this.principal = (CustomPrincipal)user;
	}

}
