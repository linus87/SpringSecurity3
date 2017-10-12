package com.linus.security.spring3.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Validate user IAF token.
 * @author lyan2
 */
public class IAFTokenAuthenticationFilter extends GenericFilterBean {

	private Logger logger = Logger.getLogger(IAFTokenAuthenticationFilter.class.getName());
	public static String USER_COOKIE_NAME = "amusr";
	
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	
	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public IAFTokenAuthenticationFilter() {
		super();
	}

	public IAFTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie userCookie = null;
		
		for (Cookie cookie : httpRequest.getCookies()) {
			if (USER_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
				userCookie = cookie;
			}
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || !auth.isAuthenticated()) {
			if (userCookie != null) {
				IAFAuthenticationToken authRequest = new IAFAuthenticationToken(userCookie.getValue(), null);
				Authentication authResult = authenticationManager.authenticate(authRequest);
				
				SecurityContextHolder.getContext().setAuthentication(authResult);
			}
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
