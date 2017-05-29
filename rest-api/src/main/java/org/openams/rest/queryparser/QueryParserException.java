package org.openams.rest.queryparser;

public class QueryParserException extends Exception{

	private static final long serialVersionUID = 4919465864809181825L;

	public QueryParserException() {
		super();
	}

	public QueryParserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QueryParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryParserException(String message) {
		super(message);
	}

	public QueryParserException(Throwable cause) {
		super(cause);
	}
	
}
