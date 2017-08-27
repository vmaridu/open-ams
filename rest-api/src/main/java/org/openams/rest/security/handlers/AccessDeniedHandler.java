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
import org.springframework.security.access.AccessDeniedException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticationFailureHandler.class);

	private ObjectMapper mapper;
	
	public AccessDeniedHandler(ObjectMapper mapper){
		this.mapper = mapper;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, 
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		LOGGER.error("Access Denied dException", accessDeniedException);
     
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ErrorMessage message = new ErrorMessage();
        message.setCode(ErrorMessageUtil.FORBIDDEN_GENERIC_ERROR_CODE);
        message.setMessage("User doesn't have enogh privileges to access this resource");
        
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        mapper.writeValue(response.getWriter(), message);
	}

}
