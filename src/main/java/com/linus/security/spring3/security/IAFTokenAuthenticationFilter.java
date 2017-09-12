package com.linus.security.spring3.security;

import java.io.IOException;
import java.net.URLDecoder;
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
	public static String IAF_TOKEN_COOKIE_NAME = "amiaf";
	public static String USER_COOKIE_NAME = "amusr";
	public static String PRINCIPAL_COOKIE_NAME = "amprin";
	
	private IAFTokenService iafTokenService;	
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
		
		Cookie iafCookie = null;
		Cookie userCookie = null;
		Cookie principalCookie = null;
		
		for (Cookie cookie : httpRequest.getCookies()) {
			if (IAF_TOKEN_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
				iafCookie = cookie;
			} else if (USER_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
				userCookie = cookie;
			} else if (PRINCIPAL_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
				principalCookie = cookie;
			}
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || !auth.isAuthenticated()) {
			if (iafCookie != null && userCookie != null) {
				String iafToken = URLDecoder.decode(iafCookie.getValue(), "UTF-8");
				IAFAuthenticationToken authRequest = new IAFAuthenticationToken(userCookie.getValue(), iafToken);
				Authentication authResult = authenticationManager.authenticate(authRequest);
				
				if (authResult.isAuthenticated()) {
					if (principalCookie != null) {
//						AMUserDetails u = (AMUserDetails)authResult.getDetails();
//						u.setAgencyMode(true);
//						u.setPrincipal(principalCookie.getValue());
					}
				}
				
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

	public IAFTokenService getIafTokenService() {
		return iafTokenService;
	}

	public void setIafTokenService(IAFTokenService iafTokenService) {
		this.iafTokenService = iafTokenService;
	}


}
