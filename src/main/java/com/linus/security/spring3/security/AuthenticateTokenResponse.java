package com.linus.security.spring3.security;

public class AuthenticateTokenResponse {
	protected AckValue ack;

	public AckValue getAck() {
		return ack;
	}

	public void setAck(AckValue ack) {
		this.ack = ack;
	}
}
