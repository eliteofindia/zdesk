package org.zdesk.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zdesk.models.CustomPrincipal;
import org.zdesk.models.Roles;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (name.equals("john")) {
			CustomPrincipal user = new CustomPrincipal();
			user.setEmail("abc@example.com");
			user.setId("1");
			user.setUsername("john");
			user.setPassword("john");
			user.setRoles(Roles.ADMIN);
			return user;
		}
		else {
			return (UserDetails) new UsernameNotFoundException(name.toUpperCase() +" USER NOT FOUND");
		}
	}

}

/*ArrayList<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
authority.add(Roles.ADMIN);
*/