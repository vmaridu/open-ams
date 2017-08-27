package org.openams.rest.integration.security;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openams.rest.TestConfig;
import org.openams.rest.utils.CommonUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthorizationTest {

	@Inject
	Environment environment;

	@Before
	public void setup() {
		CommonUtil.initRequestSpecification(environment);
	}

	@Test
	public void testAnonymousResource_whenNoAuthPassed_expect200() {

		given()
			.log().all()
		.when()
			.get("/v2/api-docs")
		.then()
			.log().all().and()
			.statusCode(200);
	}

	@Test
	public void testSecuredResource_whenNoAuthPassed_expect401() {

		given()
			.log().all()
		.when()
			.get("/api/fees")
		.then()
			.log().all().and()
			.statusCode(401)
			.body("code", equalTo(401002))
			.body("message", not(empty()));
	}

	@Test
	public void testAdminResource_whenAdminRoledJWTPassed_expect200() {

		given()
			.log().all()
			.header(HttpHeaders.AUTHORIZATION, CommonUtil.getAdminJWTAuthorization(environment))
		.when()
			.get("/api/fees")
		.then()
			.log().all().and()
			.statusCode(200);
	}

	@Test
	public void testAdminResource_whenLessPrivilegedJWTPassed_expect403() {

		given()
			.log().all()
			.header(HttpHeaders.AUTHORIZATION, CommonUtil.getStaffJWTAuthorization(environment))
		.when()
			.get("/api/fees")
		.then()
			.log().all().and()
			.statusCode(403)
			.body("code", equalTo(403000))
			.body("message", not(empty()));
	}

	@Test
	public void testStaffResource_whenStffRoledJWTPassed_expect200() {

		given()
			.log().all()
			.header(HttpHeaders.AUTHORIZATION, CommonUtil.getStaffJWTAuthorization(environment))
		.when()
			.get("/api/courses")
		.then()
			.log().all().and()
			.statusCode(200);
	}

	@Test
	public void testStaffResource_whenLessPrivilegedJWTPassed_expect403() {

		given()
			.log().all()
			.header(HttpHeaders.AUTHORIZATION, CommonUtil.getStudentJWTAuthorization(environment))
		.when()
			.get("/api/courses")
		.then()
			.log().all().and()
			.statusCode(403)
			.body("code", equalTo(403000))
			.body("message", not(empty()));
	}

	@Test
	public void testStudentResource_whenStudentRoledJWTPassed_expect200() {

		given()
			.urlEncodingEnabled(false).log().all()
			.header(HttpHeaders.AUTHORIZATION, CommonUtil.getStudentJWTAuthorization(environment))
		.when()
			.get("/api/students?filter=(%7B'user.userName'%3D%3D'brock.sosa'%7D)")
		.then()
			.log().all().and()
			.statusCode(200);
	}

}
