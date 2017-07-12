package org.openams.rest.exception;

public class ResourceNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 5991706553016515533L;

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
