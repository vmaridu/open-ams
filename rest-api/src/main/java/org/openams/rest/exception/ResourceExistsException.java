package org.openams.rest.exception;

public class ResourceExistsException extends ApplicationException {

	private static final long serialVersionUID = -220554035960692116L;

	public ResourceExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceExistsException(String message) {
		super(message);
	}
}
