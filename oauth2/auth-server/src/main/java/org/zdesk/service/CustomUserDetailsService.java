package org.zdesk.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zdesk.models.CustomPrincipal;
import org.zdesk.models.Roles;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(Roles.ADMIN);
		CustomPrincipal user =  new CustomPrincipal();
		user.setEmail("abc@example.com");
		user.setId("1");
		user.setUsername("john");
		user.setPassword("john");
		user.setRoles(Roles.ADMIN);
		return user;
	}

}
