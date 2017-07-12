package org.openams.rest.exception;

public class ValidationException extends ApplicationException {

	private static final long serialVersionUID = 8810318794779944297L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}
}
