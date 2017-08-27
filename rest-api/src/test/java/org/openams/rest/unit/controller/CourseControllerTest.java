package org.openams.rest.unit.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openams.rest.controller.CourseController;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.model.CourseModel;
import org.openams.rest.model.Page;
import org.openams.rest.service.impl.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@Mock
	private CourseService courseService;

	private CourseController controller;

	@Before
	public void setUp() {
		controller = new CourseController(courseService);
	}
	
	
	 @Test
	 public void testCreateCourse() throws ApplicationException {
		 
		 //prepare parameters to pass
		 CourseModel course = new CourseModel();
		 HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		 
		 //prepare result objects
		 CourseModel createdCourse = new CourseModel();
		 createdCourse.setId(UUID.randomUUID().toString());
		 
		 //set mocked object to return prepared result
		 when(courseService.createCourse(any(CourseModel.class))).thenReturn(createdCourse);
		 
		 //test method
		 controller.create(course, httpServletResponse);
		 
		 //validate expectations
		 verify(courseService, times(1)).createCourse(course);
		 verify(httpServletResponse, times(1)).setHeader("Location", "/api/courses/"+ createdCourse.getId());
	 }
	 
	 
	 @Test
	 public void testUpdateCourse() throws ApplicationException {
		 
		 //prepare parameters to pass
		 CourseModel course = new CourseModel();
		 String courseId = UUID.randomUUID().toString();
		 
		 //test method
		 controller.update(courseId , course);
		 
		 //validate expectations
		 verify(courseService, times(1)).updateCourse(course);
		 assertThat(course.getId(),  equalTo(courseId));
	 }
	 
	 
	 @Test
	 public void testGetCourse() throws ApplicationException {
		 
		 //prepare parameters to pass
		 String courseId = UUID.randomUUID().toString();
		 
		 //prepare result objects
		 CourseModel courseToRetrun = new CourseModel();
		 courseToRetrun.setId(courseId);
		 
		 //set mocked object to return prepared result
		 when(courseService.getCourse(eq(courseId))).thenReturn(courseToRetrun);
		 
		 //test method
		 CourseModel resultCourse = controller.getCourse(courseId);
		 
		 //validate expectations
		 verify(courseService, times(1)).getCourse(courseId);
		 assertThat(resultCourse,  equalTo(courseToRetrun));
	 }
	 
	 
	 @Test
	 public void testGetCoursesByPage() throws ApplicationException {
		 
		 //prepare parameters to pass
		 String filter = null;
		 Pageable pageRequest = new PageRequest(2, 1);
		 
		 //prepare result objects
		 Page<CourseModel> coursesToReturn = new Page<>();
		 
		 //set mocked object to return prepared result
		 when(courseService.getCourses(eq(pageRequest))).thenReturn(coursesToReturn);
		 
		 //test method
		 Page<CourseModel> resultCourses = controller.getByFilter(filter, pageRequest);
		 
		 //validate expectations
		 verify(courseService, times(1)).getCourses(pageRequest);
		 assertThat(resultCourses,  equalTo(coursesToReturn));
	 }
	 
	 
	 @Test
	 public void testGetCoursesByFilterAndPage() throws ApplicationException {
		 
		 //prepare parameters to pass
		 String filter = "({'name'=='test'})";
		 Pageable pageRequest = new PageRequest(2, 1);
		 
		 //prepare result objects
		 Page<CourseModel> coursesToReturn = new Page<>();
		 
		 //set mocked object to return prepared result
		 when(courseService.getCourses(eq(pageRequest), eq(filter))).thenReturn(coursesToReturn);
		 
		 //test method
		 Page<CourseModel> resultCourses = controller.getByFilter(filter, pageRequest);
		 
		 //validate expectations
		 verify(courseService, times(1)).getCourses(pageRequest , filter);
		 assertThat(resultCourses,  equalTo(coursesToReturn));
	 }
	 
	 
	 @Test
	 public void testDeleteCourse() throws ApplicationException {
		 
		 //prepare parameters to pass
		 String courseId = UUID.randomUUID().toString();
		 
		 //test method
		 controller.delete(courseId);
		 
		 //validate expectations
		 verify(courseService, times(1)).deleteCourse(courseId);
	 }
	 
	 
}
