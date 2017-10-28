package com.linus.security.spring3.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AMAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//	private static String domain = ".ebay.com";
	private static String path = "/";

	/**
	 * Authentication is UsernamePasswordAuthenticationToken instance.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		if (authentication != null && authentication.isAuthenticated()) {
			
			Cookie userCookie = new Cookie(TokenAuthenticationFilter.USER_COOKIE_NAME, (String)authentication.getPrincipal());
//			userCookie.setDomain(domain);
			userCookie.setPath(path);
			response.addCookie(userCookie);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
