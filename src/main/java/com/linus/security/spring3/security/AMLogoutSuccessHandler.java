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
		
		Cookie userCookie = new Cookie(TokenAuthenticationFilter.USER_COOKIE_NAME, "");
		
		userCookie.setMaxAge(0);
		response.addCookie(userCookie);
		
		super.onLogoutSuccess(request, response, authentication);
	}

}
