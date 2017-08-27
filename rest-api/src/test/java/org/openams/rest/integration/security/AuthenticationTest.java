package org.openams.rest.integration.security;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.openams.rest.utils.TestConstants.ADMIN_ACCOUNT;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openams.rest.TestConfig;
import org.openams.rest.utils.CommonUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthenticationTest {

	@Inject
	Environment environment;

	@Before
	public void setup() {
		CommonUtil.initRequestSpecification(environment);
	}

	@Test
	public void testLogin_whenValidCredentialsPassed_expect200AndJWTToken() {

		given()
			.log().all()
			.auth().preemptive().basic(ADMIN_ACCOUNT[0], ADMIN_ACCOUNT[1])
		.when()
			.get("/auth")
		.then()
			.log().all().and()
			.statusCode(200)
			.header("Authorization", not(empty()));
	}

	@Test
	public void testLogin_whenInValidCredentialsPassed_expect401AndErrorBody() {
		
		given()
			.log().all()
			.auth().preemptive().basic(ADMIN_ACCOUNT[0], ADMIN_ACCOUNT[1] + "_DIRTY")
		.when()
			.get("/auth")
		.then()
			.log().all().and()
			.statusCode(401)
			.body("code", equalTo(401001)).body("message", not(empty()));
	}

	@Test
	public void testLogin_whenNoCredentialsPassed_expect401AndErrorBody() {
		
		given()
			.log().all()
		.when()
			.get("/auth")
		.then()
			.log().all().and()
			.statusCode(401)
			.body("code", equalTo(401001))
			.body("message", not(empty()));
	}
}
