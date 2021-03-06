package com.linus.security.spring3.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author lyan2
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {
	private Logger logger = Logger.getLogger(TokenAuthenticationProvider.class.getName());
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		boolean authenticated = false;
		
		authenticated = true;
		
		if (authenticated) {
			try {
				// check if corp user is in our user list.
				UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
				AuthenticationToken authResult = new AuthenticationToken(authentication.getName(), null, user.getAuthorities());
				authResult.setDetails(user);
				return authResult;
			} catch (UsernameNotFoundException e) {
				logger.log(Level.SEVERE, String.format("A invalid user try to login amplaybok: %s" , authentication.getName()));
				return new AuthenticationToken(authentication.getName(), ((AuthenticationToken)authentication).getIafToken());
			}		
			
		}

		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
