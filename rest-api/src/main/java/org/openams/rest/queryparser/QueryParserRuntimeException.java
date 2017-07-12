package org.openams.rest.queryparser;

public class QueryParserRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 4919465864809181825L;

	public QueryParserRuntimeException() {
		super();
	}

	public QueryParserRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QueryParserRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryParserRuntimeException(String message) {
		super(message);
	}

	public QueryParserRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
