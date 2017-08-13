package org.openams.rest.security.domain;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class JwtAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	private final String token;

    public JwtAuthentication(final String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
    
}
