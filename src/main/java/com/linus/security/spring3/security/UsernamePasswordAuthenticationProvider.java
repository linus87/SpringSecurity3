package com.linus.security.spring3.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Validate user name and password.
 * 
 * @author lyan2
 */
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
	private UserDetailsService userDetailsService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
		
		if (user != null) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), user.getAuthorities());
		}

		return authentication;
	}

	/**
	 * Only supports UsernamePasswordAuthenticationToken.
	 */
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
