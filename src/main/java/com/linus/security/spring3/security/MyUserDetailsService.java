package com.linus.security.spring3.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author lyan2
 */
public class MyUserDetailsService implements UserDetailsService {
	private Map<String, UserDetails> users = new HashMap<String, UserDetails>();
	
	public MyUserDetailsService() {
		Collection<GrantedAuthority> amGrantedAuthorities = new ArrayList<GrantedAuthority>();
		Collection<GrantedAuthority> adminGrantedAuthorities = new ArrayList<GrantedAuthority>();
		Collection<GrantedAuthority> operatorGrantedAuthorities = new ArrayList<GrantedAuthority>();
		
		GrantedAuthority amAuthority = new SimpleGrantedAuthority("ROLE_AM");
		GrantedAuthority operatorAuthority = new SimpleGrantedAuthority("ROLE_OPERATOR");
		GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		
		amGrantedAuthorities.add(amAuthority);
		
		operatorGrantedAuthorities.add(amAuthority);
		operatorGrantedAuthorities.add(operatorAuthority);
		
		adminGrantedAuthorities.add(amAuthority);
		adminGrantedAuthorities.add(operatorAuthority);
		adminGrantedAuthorities.add(adminAuthority);
		
		UserDetails user = new User("user", "password", amGrantedAuthorities);
		users.put("user", user);
		
		UserDetails operator = new User("operator", "password", operatorGrantedAuthorities);
		users.put("operator", operator);
		
		UserDetails admin = new User("admin", "password", adminGrantedAuthorities);
		users.put("admin", admin);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return users.get(userName);
	}
	
}
