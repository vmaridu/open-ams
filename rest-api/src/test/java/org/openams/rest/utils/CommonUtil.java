package org.openams.rest.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;

public class CommonUtil {

	public static RequestSpecBuilder getRequestSpecBuilder(String baseUrl) {
		return new RequestSpecBuilder()
				.setConfig(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBaseUri(baseUrl);
	}
	
	public static void initRequestSpecification(String baseUrl) {
		RestAssured.requestSpecification = getRequestSpecBuilder(baseUrl).build();
	}
	
	public static void initRequestSpecification(Environment environment) {
		RestAssured.requestSpecification = getRequestSpecBuilder(environment.getProperty(Constants.BASE_URL_PROPERTY_NAME)).build();
	}
	
	public static void initAdminRoledRequestSpecification(Environment environment) {
		RestAssured.requestSpecification = getRequestSpecBuilder(environment.getProperty(Constants.BASE_URL_PROPERTY_NAME))
				.addHeader(HttpHeaders.AUTHORIZATION , CommonUtil.getAdminJWTAuthorization(environment))
				.build();
		
	}
	
	public static String getJWT(String secretKey, String baseUrl, String userName, String firstName, String lastName, String[] roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userName);
        claims.put(Constants.JWT_CLAIM_FIRST_NAME, firstName);
        claims.put(Constants.JWT_CLAIM_LAST_NAME, lastName);
        claims.put(Constants.JWT_CLAIM_ROLES, Arrays.asList(roles));
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MINUTE, Integer.valueOf(60));
        claims.put(Claims.EXPIRATION, calender.getTimeInMillis() / 1000);
        return Jwts.builder().setIssuer(baseUrl)
        		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        		.setClaims(claims)
        		.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
	}
	
	public static String getJWT(Environment environment, String userName, String firstName, String lastName, String[] roles){
		String secretKey =  environment.getProperty(Constants.SECRET_KEY_PROPERTY_NAME);
		String baseUrl =  environment.getProperty(Constants.BASE_URL_PROPERTY_NAME);
		return getJWT(secretKey, baseUrl, userName, firstName, lastName, roles );
	}
	
	public static String getJWT(Environment environment, String[] account){
		String secretKey =  environment.getProperty(Constants.SECRET_KEY_PROPERTY_NAME);
		String baseUrl =  environment.getProperty(Constants.BASE_URL_PROPERTY_NAME);
		return getJWT(secretKey, baseUrl, account[0], account[2], account[3], account[4].split(","));
	}
	
	public static String getAdminJWTAuthorization(Environment environment){
		return "Bearer " + getJWT(environment, TestConstants.ADMIN_ACCOUNT);
	}
	
	public static String getStaffJWTAuthorization(Environment environment){
		return "Bearer " + getJWT(environment, TestConstants.STAFF_ACCOUNT);
	}
	
	public static String getStudentJWTAuthorization(Environment environment){
		return "Bearer " + getJWT(environment, TestConstants.STUDENT_ACCOUNT);
	}
	
}
