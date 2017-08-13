package org.openams.rest.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.security.domain.JwtAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class JWTAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
    private final AuthenticationFailureHandler failureHandler;

    public JWTAuthenticationProcessingFilter(final String defaultFilterProcessesUrl, final AuthenticationManager authenticationManager, 
    		final AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.failureHandler = failureHandler;
        setAuthenticationManager(authenticationManager);
    }

    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    	String jwtAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    	String jwt = null;
    	if(StringUtils.isNotBlank(jwtAuthorization)){
    		jwt = jwtAuthorization.replaceFirst("Bearer ", "");
    	}
        return getAuthenticationManager().authenticate(new JwtAuthentication(jwt));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
    
    
}
