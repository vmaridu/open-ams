package org.openams.rest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(IllegalArgumentException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleException(EntityExistsException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleException(EntityNotFoundException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}


}
