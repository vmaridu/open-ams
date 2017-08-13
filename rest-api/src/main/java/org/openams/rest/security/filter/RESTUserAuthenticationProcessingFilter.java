package org.openams.rest.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openams.rest.security.domain.JwtAuthentication;
import org.openams.rest.security.domain.UserBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RESTUserAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    public RESTUserAuthenticationProcessingFilter(final String defaultFilterProcessesUrl, final AuthenticationManager authenticationManager, 
    		final AuthenticationSuccessHandler successHandler, final AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        setAuthenticationManager(authenticationManager);
    }

    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    	String basicAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        return getAuthenticationManager().authenticate(new UserBasicAuthentication(basicAuthorization));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtAuthentication jwtToken = (JwtAuthentication) authResult;
        response.setHeader(HttpHeaders.AUTHORIZATION, (String) jwtToken.getPrincipal());
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
    
    
}
