package org.openams.rest.integration.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openams.rest.TestConfig;
import org.openams.rest.model.CourseModel;
import org.openams.rest.utils.CommonUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class, webEnvironment= WebEnvironment.DEFINED_PORT)
public class CourseControllerTest {
	
	@Inject 
	Environment environment;
	
	@Before
	public void setup() {
		CommonUtil.initAdminRoledRequestSpecification(environment);
	}
	
	@Test
	public void testGetAllCourses_whenNoPageRequestOrFilterPassed_expect200AndCoursesPage() {
		
	    given()
		  .log().all()
		.when()
		  .get("/api/courses")
		.then()
		  .log().all().and()
		  .statusCode(200)
		  .body("page", equalTo(0))
		  .body("size", equalTo(4))
		  .body("limit", equalTo(10))
	      .body("prvious", equalTo(null))
		  .body("next", equalTo(null))
		  .body("total", equalTo(4));
	}
	
	@Test
	public void testGetAllCourses_whenOnlyPageRequestPassed_expect200AndCoursesPage() {
		
	    given()
	      .urlEncodingEnabled(false)
		  .log().all()
		.when()
		  .get("/api/courses?page=0&limit=2")
		.then()
		  .log().all().and()
		  .statusCode(200)
		  .body("page", equalTo(0))
		  .body("size", equalTo(2))
	      .body("limit", equalTo(2))
	      .body("prvious", equalTo(null))
		  .body("next", equalTo("page=1&limit=2"))
		  .body("total", equalTo(4));
	}
	
	@Test
	public void testGetAllCourses_whenOnlyFilter_expect200AndCoursesPage() {
		
	    given()
	      .urlEncodingEnabled(false)
		  .log().all()
		.when()
		  .get("/api/courses?filter=(%7B'dept'%3D%3D'MATH'%7D)")
		.then()
		  .log().all().and()
		  .statusCode(200)
		  .body("page", equalTo(0))
		  .body("size", equalTo(2))
	      .body("limit", equalTo(10))
	      .body("prvious", equalTo(null))
		  .body("next", equalTo(null))
		  .body("total", equalTo(2));
	}	
	
	@Test
	public void testCreateCourse_whenValidCoursePassed_expect201AndCreatedId() {
		
		CourseModel course = new CourseModel();
		course.setName("K13 MATH");
		course.setDept("MATH");
		course.setDesc("M");
		course.setCredits((byte)3);
		course.setModifiedDtt(new Date());
		
	    Response response = given()
	      .contentType(ContentType.JSON)
	      .body(course)
		  .log().all()
		.when()
		  .post("/api/courses")
		.then()
		  	.log().all().and()
		  	.statusCode(201)
		.extract()
			.response();
	    
	    String createdCourseLocation = response.getHeader("Location");
	    assertThat(createdCourseLocation,  not(isEmptyOrNullString()));
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, statements = 
	{"INSERT INTO `course` VALUES ('0b735b88-1b26-4b7b-8f5c-7f3939ecfb2l','K13 MATH','MATH','K13 Mathematics',NULL,'2016-04-10 16:28:25')"})
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, statements = 
	{"DELETE FROM `course` WHERE ID = '0b735b88-1b26-4b7b-8f5c-7f3939ecfb2l'"})
	public void testGetCourseById_whenIdPassed_expect200AndCourse() {
		
	     given()
		  .log().all()
		.when()
		  .get("/api/courses/0b735b88-1b26-4b7b-8f5c-7f3939ecfb2l")
		.then()
		  .log().all().and()
		  .statusCode(200)
		  .body("name", equalTo("K13 MATH"));
	}
	
	@Test
	public void testGetCourseById_whenNonExistingIdPassed_expect404() {
		
	     given()
		  .log().all()
		.when()
		  .get("/api/courses/0b735b88-1b26-4b7b-8z5c-7f3939ecfb2a")
		.then()
		  .log().all().and()
		  .statusCode(404);
	}
	
	@Test
	public void testUpdateCourse_whenValidCoursePassed_expect204() {
		
		CourseModel course = new CourseModel();
		course.setName("K13 MATH");
		course.setDept("MATH");
		course.setDesc("M");
		course.setCredits((byte)3);
		course.setModifiedDtt(new Date());
		
	    given()
	      .contentType(ContentType.JSON)
	      .body(course)
		  .log().all()
		.when()
		  .put("/api/courses/0b735b88-1b26-4b7b-8f5c-7f3939ecfb2a")
		.then()
		  	.log().all().and()
		  	.statusCode(204);
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, statements = 
	{"INSERT INTO `course` VALUES ('0b735b88-1b26-4b7b-8f5c-7f3939ecfb2h','K13 MATH','MATH','K13 Mathematics',NULL,'2016-04-10 16:28:25')"})
	public void testDeleteCourseById_whenIdPassed_expect204() {
		
	     given()
		  .log().all()
		.when()
		  .delete("/api/courses/0b735b88-1b26-4b7b-8f5c-7f3939ecfb2h")
		.then()
		  .log().all().and()
		  .statusCode(204);
	}
	
}
