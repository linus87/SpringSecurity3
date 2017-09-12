package com.linus.security.spring3.security;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * @author lyan2
 */
public class IAFTokenService {
	private static Logger logger = Logger.getLogger(IAFTokenService.class.getName());
	public static int retryLimit = 2;
	
	public static void main(String[] args) {
		
	}

	public AuthenticateTokenResponse authenticateToken(String token)  {
		return authenticateToken(token, 1);
	}
	
	private AuthenticateTokenResponse authenticateToken(String token, int count) {
		if (count > retryLimit) {
			logger.log(Level.WARNING, String.format("IAF authenticateToken() has been called more than %d times.", retryLimit));
			return null;
		}
		
		AuthenticateTokenResponse  response = null;
		
		return response; 
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	public String getUserToken(String userName) {
		
		return userName;
	}
	
}
