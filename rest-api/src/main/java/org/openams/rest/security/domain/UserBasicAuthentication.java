package org.openams.rest.security.domain;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import io.jsonwebtoken.lang.Assert;

public class UserBasicAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String authorizationHeader;
	private String userName;
	private String password;

	public UserBasicAuthentication(final String authorizationHeader) {
		super(null);
		this.authorizationHeader = authorizationHeader;
	}

	public void decodeAndSetCredentials() {
		Assert.isTrue(StringUtils.isNotEmpty(authorizationHeader), "Authorization can't be null");
		if (StringUtils.startsWith(authorizationHeader, "Basic ")) { 
			String encodedCredentials = authorizationHeader.replaceFirst("Basic ", "");
			String[] credentials = new String(Base64.getDecoder().decode(encodedCredentials)).split(":");
			if(credentials.length == 2){
				this.userName = credentials[0];
				this.password = credentials[1];
			}
		}
		Assert.isTrue(StringUtils.isNoneEmpty(userName, password), "Invalid Authorization : userName and password can't be null");
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return authorizationHeader;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
