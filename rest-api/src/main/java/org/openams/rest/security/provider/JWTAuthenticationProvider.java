package org.openams.rest.security.provider;


import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.security.domain.JwtAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    private final String secretKey;

    @Autowired
    public JWTAuthenticationProvider(@Value("${secret.key}") final String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(JwtAuthentication.class, authentication);        
        try {
        	JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
        	String jwt = (String) jwtAuthentication.getPrincipal();
        	Assert.isTrue(StringUtils.isNotEmpty(jwt), "Invalid Authorization : JWT can't be null");
            Claims claims  = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwt).getBody();
            Collection<? extends GrantedAuthority> authorities = ( (Collection<String>) claims.get(org.openams.rest.utils.Constants.JWT_CLAIM_ROLES))
            												.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            String subject = (String) claims.get(Claims.SUBJECT);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(subject, jwt, authorities);
            return usernamePasswordAuthenticationToken;
        } catch (Exception e) {
        	LOGGER.error("JWT validation failed");
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthentication.class);
    }

}
