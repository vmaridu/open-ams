package org.openams.rest.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openams.rest.queryparser.QueryParserRuntimeException;

public class ConverterUtil {
	
	private static String DATE_FORMAT = "yyyy-MM-dd";
	private static String DATE_TIME_FORMAT = "yyyy-MM-dd-HH-mm-ss";

	public static Date toDate(String input) throws QueryParserRuntimeException{
		try{
			return new SimpleDateFormat(DATE_FORMAT).parse(input);
		}catch (Exception e) {
			throw new QueryParserRuntimeException("Unable to input to Date format" , e);
		}
	}
	
	public static Date toDateTime(String input){
		try{
			return new SimpleDateFormat(DATE_TIME_FORMAT).parse(input);
		}catch (Exception e) {
			throw new QueryParserRuntimeException("Unable to input to Date format" , e);
		}
	}
	
	public static Time toTime(String input){
		return Time.valueOf(input.replaceAll("-", ":"));
	}
}
