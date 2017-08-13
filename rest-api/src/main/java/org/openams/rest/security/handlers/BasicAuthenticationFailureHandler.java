package org.openams.rest.security.handlers;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openams.rest.model.ErrorMessage;
import org.openams.rest.utils.ErrorMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BasicAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticationFailureHandler.class);

	private ObjectMapper mapper;
    
	public BasicAuthenticationFailureHandler(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LOGGER.error("Basic Authentication failed",exception);
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        //prepare authentication error message with redirect url
        ErrorMessage message = new ErrorMessage();
        message.setCode(ErrorMessageUtil.UNAUTHORIZED_BASIC_AUTH_ERROR_CODE);
        message.setMessage("Basic Authentication Failed");
        message.setDeveloperMessage("Pass userName and password by Basic Authentication; 'Authorization = Basic Base64Encoded<userName:password>'");
        
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        mapper.writeValue(response.getWriter(), message);
    }

}
