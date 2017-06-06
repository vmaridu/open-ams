package org.openams.rest.utils;

import java.util.stream.Collectors;

import org.openams.rest.model.ErrorMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorMessageUtil {

	public static final int BAD_REQUEST_GENERIC_ERROR_CODE = 400000;
	public static final int BAD_REQUEST_QUERY_PARSER_ERROR_CODE = 400001;
	public static final int NOT_FOUND_GENERIC_ERROR_CODE = 404000;
	public static final int CONFLICT_GENERIC_ERROR_CODE = 40900;
	public static final int INTERNAL_SERVER_ERROR_GENERIC_ERROR_CODE = 50000;
	
	public static final int VALIDATION_ERROR_CODE = 400001;
	
	
	public static ErrorMessage getErrorMessage(int code, String message, String developerMessgae, String moreInfo){
		return new ErrorMessage(code, message, developerMessgae, moreInfo);
	}
	
	public static ErrorMessage getErrorMessage(int code, Exception exception){
		return new ErrorMessage(code, exception.getMessage(), null, null);
	}
	
	public static ErrorMessage getErrorMessage(int code, String message){
		return new ErrorMessage(code, message, null, null);
	}

	public static ErrorMessage getMethodArgumentNotValidErrorMessage(MethodArgumentNotValidException excepton){
		BindingResult bindingResult = excepton.getBindingResult();
		
		String errorMessage = bindingResult.getAllErrors().stream().map(error ->  error.getDefaultMessage()).collect(Collectors.joining( "," ));
		return new ErrorMessage(BAD_REQUEST_GENERIC_ERROR_CODE, "Validation ERROR : " + errorMessage, null, null);
	}
	
}
