package org.openams.rest.service.impl;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.repository.CourseScheduleRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.CourseScheduleModel;
import org.openams.rest.model.Page;
import org.openams.rest.queryparser.CourseScheduleQueryParser;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CourseScheduleService {

	//private RepositoryWrapper<Staff, String> staffRepository;
	//private RepositoryWrapper<Course, String> courseRepository;
	private RepositoryWrapper<CourseSchedule, String> courseScheduleRepository;
	private Mapper mapper;
	private CourseScheduleQueryParser parser;

	@Autowired
	public CourseScheduleService(CourseScheduleRepository courseScheduleRepository, Mapper mapper, CourseScheduleQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		//StaffRepository staffRepository, CourseRepository courseRepository ,
		//this.staffRepository = new RepositoryWrapper<Staff, String>(staffRepository, (Staff::getId));
		//this.courseRepository = new RepositoryWrapper<Course, String>(courseRepository, (Course::getId));
		this.courseScheduleRepository = new RepositoryWrapper<CourseSchedule, String>(courseScheduleRepository, (CourseSchedule::getId));
	}
	
	public Page<CourseScheduleModel> getCourseSchedules(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<CourseSchedule> courseSchedules = courseScheduleRepository.findAll(pageRequest);
		Function<CourseSchedule, CourseScheduleModel> contentMapper = (courseSchedule) -> mapper.map(courseSchedule, CourseScheduleModel.class);
		return PaginationUtil.toPageModel(courseSchedules, pageRequest, contentMapper);
	}

	public Page<CourseScheduleModel> getCourseSchedules(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<CourseSchedule> courseSchedules = courseScheduleRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<CourseSchedule, CourseScheduleModel> contentMapper = (courseSchedule) -> mapper.map(courseSchedule, CourseScheduleModel.class);
		return PaginationUtil.toPageModel(courseSchedules, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public CourseScheduleModel getCourseSchedule(String id) {
		CourseSchedule courseSchedule = courseScheduleRepository.findOne(id);
		CourseScheduleModel courseScheduleModel = mapper.map(courseSchedule, CourseScheduleModel.class);
		return courseScheduleModel;
	}
	
	public CourseScheduleModel createCourseSchedule(CourseScheduleModel courseScheduleModel){
		CourseSchedule courseSchedule = mapper.map(courseScheduleModel, CourseSchedule.class);
		//setCourse(courseSchedule,courseScheduleModel.getCourseId());
		//setStaff(courseSchedule,courseScheduleModel.getInstructorId());
		courseScheduleRepository.create(courseSchedule);
		CourseScheduleModel createdCourseScheduleModel = mapper.map(courseSchedule, CourseScheduleModel.class);
		return createdCourseScheduleModel;
	}
	
	public void updateCourseSchedule(CourseScheduleModel courseScheduleModel){
		CourseSchedule courseSchedule = mapper.map(courseScheduleModel, CourseSchedule.class);
		//setCourse(courseSchedule,courseScheduleModel.getCourseId());
		//setStaff(courseSchedule,courseScheduleModel.getInstructorId());
		courseScheduleRepository.update(courseSchedule);
	}
	
	public void deleteCourseSchedule(String id){
		courseScheduleRepository.delete(id);
	}
	
	//private void setStaff(CourseSchedule courseSchedule, String staffId){
	//	courseSchedule.setStaff(staffRepository.findOne(staffId));
	//}
	
	//private void setCourse(CourseSchedule courseSchedule, String courseId){
	//	courseSchedule.setCourse(courseRepository.findOne(courseId));
	//}
	
}
