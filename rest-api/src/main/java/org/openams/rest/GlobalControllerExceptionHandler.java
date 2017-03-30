package org.openams.rest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = {IllegalArgumentException.class, DataIntegrityViolationException.class, PropertyReferenceException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequests(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleConflictException(EntityExistsException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = {EntityNotFoundException.class, NotImplementedException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNotFoundException(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}


}
