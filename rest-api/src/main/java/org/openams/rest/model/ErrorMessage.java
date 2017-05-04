package org.openams.rest.model;

import lombok.Data;

@Data
public class ErrorMessage {

	private int code;
	private String message;
	private String developerMessage;
	private String moreInfo;
	

	public ErrorMessage() { }

	public ErrorMessage(int code, String message, String developerMesage, String moreInfo) {
		this.code = code;
		this.message = message;
		this.developerMessage = developerMesage;
		this.moreInfo = moreInfo;
	}
	
}
