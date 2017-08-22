package org.openams.rest.utils;

import java.util.Optional;

import org.apache.log4j.MDC;

public class LogUtils {

	public static String EMPTY_STRING = "";
	
	public static String getTxId(){
		return Optional.ofNullable((String) MDC.get(Constants.TX_ID)).orElse(EMPTY_STRING);
	}
	

	
}
