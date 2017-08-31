package org.openams.rest.security.provider;


import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.PersonRepository;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.security.domain.JwtAuthentication;
import org.openams.rest.security.domain.UserBasicAuthentication;
import org.openams.rest.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    private final int tokenDuration;
    private final String secretKey;
    private final String baseUrl;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    BCryptPasswordEncoder encoder;

    @Autowired
    public UserAuthenticationProvider(@Value("${jwt.token.ttl}") final int tokenDuration, 
    		@Value("${secret.key}") final String secretKey, 
    		@Value("${baseUrl}") final String baseUrl, 
    		final UserRepository userRepository,
    		PersonRepository personRepository) {
        this.tokenDuration = tokenDuration;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	LOGGER.info(String.format("Basic Authenticating ...; TX_ID (%s) ", getTxId()));
        Assert.isInstanceOf(UserBasicAuthentication.class, authentication);        
        try {
        	UserBasicAuthentication basicAuthrntication = (UserBasicAuthentication) authentication;
        	basicAuthrntication.decodeAndSetCredentials();        	
            User user = userRepository.findById(basicAuthrntication.getUserName()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            
            if (!encoder.matches(basicAuthrntication.getPassword(), user.getPassword())){
    			throw new AccessDeniedException("Password doesn't match");
    		}
            
            String jwtToken = createToken(user);
            LOGGER.info(String.format("Basic Authentication Successful; TX_ID (%s) UserName(%s)", getTxId(), basicAuthrntication.getUserName()));
            return new JwtAuthentication(jwtToken);
        } catch (Exception e) {
        	LOGGER.error(String.format("Basic Authentication Failed; TX_ID (%s)", getTxId()),e);
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserBasicAuthentication.class);
    }

 
    private String createToken(final User user) {
        // Set expire date.
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MINUTE, Integer.valueOf(tokenDuration));
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, user.getUserName());
        claims.put(Claims.EXPIRATION, calender.getTimeInMillis() / 1000);
        claims.put(Constants.JWT_CLAIM_ROLES, user.getRoles().stream().map(Role :: getName).collect(Collectors.toSet()));
        personRepository.findByUserName(user.getUserName()).stream().findFirst().ifPresent(p -> {
        	claims.put(Constants.JWT_CLAIM_FIRST_NAME, p.getFName());
        	claims.put(Constants.JWT_CLAIM_LAST_NAME, p.getLName());
        });
        // Create JWT token.
        return Jwts.builder().setIssuer(baseUrl)
        		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        		.setClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
        		.compact();
    }


}
