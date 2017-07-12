package org.openams.rest.service.impl;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Course;
import org.openams.rest.jpa.repository.CourseRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.CourseModel;
import org.openams.rest.model.Page;
import org.openams.rest.queryparser.CourseQueryParser;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CourseService {

	private RepositoryWrapper<Course, String> courseRepository;
	private Mapper mapper;
	private CourseQueryParser parser;

	@Autowired
	public CourseService(CourseRepository courseRepository, Mapper mapper, CourseQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.courseRepository = new RepositoryWrapper<Course, String>(courseRepository, (Course::getId));
	}
	
	public Page<CourseModel> getCourses(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<Course> courses = courseRepository.findAll(pageRequest);
		Function<Course, CourseModel> contentMapper = (course) -> mapper.map(course, CourseModel.class);
		return PaginationUtil.toPageModel(courses, pageRequest, contentMapper);
	}

	public Page<CourseModel> getCourses(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Course> courses = courseRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Course, CourseModel> contentMapper = (course) -> mapper.map(course, CourseModel.class);
		return PaginationUtil.toPageModel(courses, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public CourseModel getCourse(String id) {
		Course course = courseRepository.findOne(id);
		CourseModel courseModel = mapper.map(course, CourseModel.class);
		return courseModel;
	}
	
	public CourseModel createCourse(CourseModel courseModel){
		Course course = mapper.map(courseModel, Course.class);
		courseRepository.create(course);
		CourseModel createdCourseModel = mapper.map(course, CourseModel.class);
		return createdCourseModel;
	}
	
	public void updateCourse(CourseModel courseModel){
		Course course = mapper.map(courseModel, Course.class);
		courseRepository.update(course);
	}
	
	public void deleteCourse(String id){
		courseRepository.delete(id);
	}
	
}
