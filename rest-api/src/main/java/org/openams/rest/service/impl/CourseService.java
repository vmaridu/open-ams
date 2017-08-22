package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.Course;
import org.openams.rest.jpa.repository.CourseRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.CourseModel;
import org.openams.rest.model.Page;
import org.openams.rest.queryparser.CourseQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CourseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
	
	private RepositoryWrapper<Course, String> courseRepository;
	private Mapper mapper;
	private CourseQueryParser parser;

	@Autowired
	public CourseService(CourseRepository courseRepository, Mapper mapper, CourseQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.courseRepository = new RepositoryWrapper<Course, String>(courseRepository, (Course::getId));
	}
	
	public Page<CourseModel> getCourses(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get Courses Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Course> courses = courseRepository.findAll(pageRequest);
		Function<Course, CourseModel> contentMapper = (course) -> mapper.map(course, CourseModel.class);
		Page<CourseModel> result = PaginationUtil.toPageModel(courses, pageRequest, contentMapper);
		LOGGER.info("Processed Get Courses Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<CourseModel> getCourses(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get Courses Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Course> courses = courseRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Course, CourseModel> contentMapper = (course) -> mapper.map(course, CourseModel.class);
		Page<CourseModel> result = PaginationUtil.toPageModel(courses, pageRequest, contentMapper);
		LOGGER.info("Processed Get Courses Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public CourseModel getCourse(String id) throws ApplicationException {
		LOGGER.info("Processing Get Course By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		Course course = courseRepository.findOne(id);
		CourseModel courseModel = mapper.map(course, CourseModel.class);
		LOGGER.info("Processed Get Course By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return courseModel;
	}
	
	public CourseModel createCourse(CourseModel courseModel) throws ApplicationException {
		LOGGER.info("Processing Create Course Request ...; TX_ID ({})", getTxId());
		Course course = mapper.map(courseModel, Course.class);
		courseRepository.create(course);
		CourseModel createdCourseModel = mapper.map(course, CourseModel.class);
		LOGGER.info("Processed Create Course Request; TX_ID ({}) createdID ({})", getTxId(), createdCourseModel.getId());
		return createdCourseModel;
	}
	
	public void updateCourse(CourseModel courseModel) throws ApplicationException {
		LOGGER.info("Processing Update Course Request ...; TX_ID ({}) ID ({})", getTxId(), courseModel.getId());
		Course course = mapper.map(courseModel, Course.class);
		courseRepository.update(course);
		LOGGER.info("Processed Update Course Request; TX_ID ({}) ID ({})", getTxId(), courseModel.getId());
	}
	
	public void deleteCourse(String id) throws ApplicationException {
		LOGGER.info("Processing Delete Course Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		courseRepository.delete(id);
		LOGGER.info("Processed Delete Course Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
