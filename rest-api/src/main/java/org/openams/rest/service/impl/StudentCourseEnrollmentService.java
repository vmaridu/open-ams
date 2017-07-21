package org.openams.rest.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.openams.rest.jpa.repository.CourseScheduleRepository;
import org.openams.rest.jpa.repository.StudentCourseEnrollmentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.StudentCourseEnrollmentReportModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentCourseEnrollmentModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.queryparser.StudentCourseEnrollmentQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentCourseEnrollmentService {

	private RepositoryWrapper<CourseSchedule, String> courseScheduleRepository;
	private RepositoryWrapper<StudentCourseEnrollment, String> studentCourseEnrollmentRepository;
	private Mapper mapper;
	private StudentCourseEnrollmentQueryParser parser;

	@Autowired
	public StudentCourseEnrollmentService(CourseScheduleRepository courseScheduleRepository,
			StudentCourseEnrollmentRepository studentCourseEnrollmentRepository, Mapper mapper, StudentCourseEnrollmentQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.courseScheduleRepository = new RepositoryWrapper<CourseSchedule, String>(courseScheduleRepository, (CourseSchedule::getId));
		this.studentCourseEnrollmentRepository = new RepositoryWrapper<StudentCourseEnrollment, String>(studentCourseEnrollmentRepository, (StudentCourseEnrollment::getId));
	}
	
	public Page<StudentCourseEnrollmentModel> getStudentCourseEnrollments(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<StudentCourseEnrollment> studentCourseEnrollments = studentCourseEnrollmentRepository.findAll(pageRequest);
		Function<StudentCourseEnrollment, StudentCourseEnrollmentModel> contentMapper = (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		return PaginationUtil.toPageModel(studentCourseEnrollments, pageRequest, contentMapper);
	}

	public Page<StudentCourseEnrollmentModel> getStudentCourseEnrollments(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<StudentCourseEnrollment> studentCourseEnrollments = studentCourseEnrollmentRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<StudentCourseEnrollment, StudentCourseEnrollmentModel> contentMapper = (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		return PaginationUtil.toPageModel(studentCourseEnrollments, pageRequest, contentMapper);
	}
	
	public Collection<StudentCourseEnrollmentReportModel> getFlatStudentCourseEnrollmentsByCourseScheduleId(String courseScheduleId) {
		
		//throw EntityNotFound Exception if course schedule not found
		courseScheduleRepository.findOne(courseScheduleId);
				
		StudentCourseEnrollmentRepository repository = (StudentCourseEnrollmentRepository) studentCourseEnrollmentRepository.getRepository();
		return repository.findByCourseScheduleId(courseScheduleId).stream()
				.map( (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentReportModel.class))
				.collect(Collectors.toList());
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public StudentCourseEnrollmentModel getStudentCourseEnrollment(String id) {
		StudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentRepository.findOne(id);
		StudentCourseEnrollmentModel studentCourseEnrollmentModel = mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		return studentCourseEnrollmentModel;
	}
	
	public StudentCourseEnrollmentModel createStudentCourseEnrollment(StudentCourseEnrollmentModel studentCourseEnrollmentModel){
		StudentCourseEnrollment studentCourseEnrollment = mapper.map(studentCourseEnrollmentModel, StudentCourseEnrollment.class);
		studentCourseEnrollmentRepository.create(studentCourseEnrollment);
		StudentCourseEnrollmentModel createdStudentCourseEnrollmentModel = mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		return createdStudentCourseEnrollmentModel;
	}
	
	public void updateStudentCourseEnrollment(StudentCourseEnrollmentModel studentCourseEnrollmentModel){
		StudentCourseEnrollment studentCourseEnrollment = mapper.map(studentCourseEnrollmentModel, StudentCourseEnrollment.class);
		studentCourseEnrollmentRepository.update(studentCourseEnrollment);
	}
	
	public void deleteStudentCourseEnrollment(String id){
		studentCourseEnrollmentRepository.delete(id);
	}
	
}
