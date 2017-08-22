package org.openams.rest.config;

import static org.openams.rest.utils.ErrorMessageUtil.BAD_REQUEST_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.BAD_REQUEST_QUERY_PARSER_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.CONFLICT_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.INTERNAL_SERVER_ERROR_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.ErrorMessageUtil.NOT_FOUND_GENERIC_ERROR_CODE;
import static org.openams.rest.utils.LogUtils.getTxId;

import org.apache.commons.lang3.NotImplementedException;
import org.openams.rest.exception.QueryParserException;
import org.openams.rest.exception.ResourceExistsException;
import org.openams.rest.exception.ResourceNotFoundException;
import org.openams.rest.model.ErrorMessage;
import org.openams.rest.queryparser.QueryParserRuntimeException;
import org.openams.rest.utils.ErrorMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	 
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		LOGGER.error(String.format("Illegal Argument; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		LOGGER.error(String.format("DataIntegrity Violation; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handlePropertyReferenceException(PropertyReferenceException e) {
		LOGGER.error(String.format("Property Reference Exception; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		LOGGER.error(String.format("Method Argument Not Valid; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getMethodArgumentNotValidErrorMessage(e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleQueryParserException(QueryParserException e) {
		LOGGER.error(String.format("Invalid Filter Query; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_QUERY_PARSER_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleQueryParserRuntimeException(QueryParserRuntimeException e) {
		LOGGER.error(String.format("Invalid Filter Query; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(BAD_REQUEST_QUERY_PARSER_ERROR_CODE, e);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorMessage handleResourceExistsException(ResourceExistsException e) {
		LOGGER.error(String.format("Resource Already Exist; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(CONFLICT_GENERIC_ERROR_CODE, e);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException e) {
		LOGGER.error(String.format("Resource Not Found; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(NOT_FOUND_GENERIC_ERROR_CODE, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleNotImplementedException(NotImplementedException e) {
		LOGGER.error(String.format("Not Implemented; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(NOT_FOUND_GENERIC_ERROR_CODE, "Implementation NOT FOUND" , e.getMessage(), null);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception e) {
		LOGGER.error(String.format("Server Error; TX_ID (%s)", getTxId()), e);
		return ErrorMessageUtil.getErrorMessage(INTERNAL_SERVER_ERROR_GENERIC_ERROR_CODE, e);
	}


}
