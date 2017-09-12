package com.linus.security.spring3.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Remove all cookie values.
 * @author lyan2
 */
public class AMLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		Cookie iafCookie = new Cookie(IAFTokenAuthenticationFilter.IAF_TOKEN_COOKIE_NAME, "");
		Cookie userCookie = new Cookie(IAFTokenAuthenticationFilter.USER_COOKIE_NAME, "");
		Cookie principalCookie = new Cookie(IAFTokenAuthenticationFilter.PRINCIPAL_COOKIE_NAME, "");
		
		iafCookie.setMaxAge(0);
		userCookie.setMaxAge(0);
		principalCookie.setMaxAge(0);
		response.addCookie(iafCookie);
		response.addCookie(userCookie);
		response.addCookie(principalCookie);
		
		super.onLogoutSuccess(request, response, authentication);
	}

}
