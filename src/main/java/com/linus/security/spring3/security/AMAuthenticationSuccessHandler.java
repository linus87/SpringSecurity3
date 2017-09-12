package com.linus.security.spring3.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AMAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static String domain = ".ebay.com";
	private static String path = "/";

	/**
	 * Authentication is UsernamePasswordAuthenticationToken instance.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		if (authentication instanceof IAFAuthenticationToken) {
			IAFAuthenticationToken auth = (IAFAuthenticationToken)authentication;
			
			Cookie iafCookie = new Cookie(IAFTokenAuthenticationFilter.IAF_TOKEN_COOKIE_NAME, URLEncoder.encode(auth.getIafToken(), "UTF-8"));
			Cookie userCookie = new Cookie(IAFTokenAuthenticationFilter.USER_COOKIE_NAME, (String)auth.getPrincipal());
			iafCookie.setDomain(domain);
			userCookie.setDomain(domain);
			iafCookie.setPath(path);
			userCookie.setPath(path);
			response.addCookie(iafCookie);
			response.addCookie(userCookie);
			
			DefaultUserDetails details = (DefaultUserDetails)auth.getCredentials();
			
			if (details != null && details.isAgencyMode()) {
				Cookie principalCookie = new Cookie(IAFTokenAuthenticationFilter.PRINCIPAL_COOKIE_NAME, details.getPrincipal());
				principalCookie.setDomain(domain);
				principalCookie.setDomain(path);
				response.addCookie(principalCookie);
			}
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
