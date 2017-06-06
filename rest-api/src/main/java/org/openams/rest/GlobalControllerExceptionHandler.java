package org.openams.rest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.NotImplementedException;
import org.openams.rest.model.ErrorMessage;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.utils.ErrorMessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.openams.rest.utils.ErrorMessageUtil.BAD_REQUEST_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.NOT_FOUND_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.CONFLICT_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.INTERNAL_SERVER_ERROR_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.BAD_REQUEST_QUERY_PARSER_ERROR_CODE;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handlePropertyReferenceException(PropertyReferenceException e) {
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ErrorMessageUtil.getMethodArgumentNotValidErrorMessage(e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleQueryParserException(QueryParserException e) {
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_QUERY_PARSER_ERROR_CODE, e);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorMessage handleConflictException(EntityExistsException e) {
		return ErrorMessageUtil.getErrorMessage(CONFLICT_GENERIC_ERROR_CODE, e);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleEntityNotFoundException(EntityNotFoundException e) {
		return ErrorMessageUtil.getErrorMessage(NOT_FOUND_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleNotImplementedException(NotImplementedException e) {
		return ErrorMessageUtil.getErrorMessage(NOT_FOUND_GENERIC_ERROR_CODE, "Requested resource NOT FOUND" , e.getMessage(), null);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception e) {
		return ErrorMessageUtil.getErrorMessage(INTERNAL_SERVER_ERROR_GENERIC_ERROR_CODE, e);
	}


}
