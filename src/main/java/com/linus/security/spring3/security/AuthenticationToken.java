package com.linus.security.spring3.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * IAF Authentication Token Holder.
 * @author lyan2
 */
public class AuthenticationToken extends AbstractAuthenticationToken {
	
	private String iafToken;
	private final Object principal;
	
	public AuthenticationToken(Object principal, String iafToken) {
		super(null);
		this.principal = principal;
		this.iafToken = iafToken;
		this.setAuthenticated(false);
	}
	
	public AuthenticationToken(Object principal, String iafToken, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.iafToken = iafToken;
		this.setAuthenticated(true);
	}

	private static final long serialVersionUID = -3271591829802419528L;

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	public String getIafToken() {
		return iafToken;
	}

}
