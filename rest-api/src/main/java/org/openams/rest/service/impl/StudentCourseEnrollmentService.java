package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.openams.rest.jpa.repository.CourseScheduleRepository;
import org.openams.rest.jpa.repository.StudentCourseEnrollmentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentCourseEnrollmentModel;
import org.openams.rest.model.StudentCourseEnrollmentReportModel;
import org.openams.rest.queryparser.StudentCourseEnrollmentQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentCourseEnrollmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentCourseEnrollmentService.class);
	
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
	
	public Page<StudentCourseEnrollmentModel> getStudentCourseEnrollments(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get StudentCourseEnrollmen Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<StudentCourseEnrollment> studentCourseEnrollments = studentCourseEnrollmentRepository.findAll(pageRequest);
		Function<StudentCourseEnrollment, StudentCourseEnrollmentModel> contentMapper = (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		Page<StudentCourseEnrollmentModel> result = PaginationUtil.toPageModel(studentCourseEnrollments, pageRequest, contentMapper);
		LOGGER.info("Processed Get StudentCourseEnrollmen Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<StudentCourseEnrollmentModel> getStudentCourseEnrollments(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get StudentCourseEnrollmen Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<StudentCourseEnrollment> studentCourseEnrollments = studentCourseEnrollmentRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<StudentCourseEnrollment, StudentCourseEnrollmentModel> contentMapper = (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		Page<StudentCourseEnrollmentModel> result = PaginationUtil.toPageModel(studentCourseEnrollments, pageRequest, contentMapper);
		LOGGER.info("Processed Get StudentCourseEnrollmen Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Collection<StudentCourseEnrollmentReportModel> getFlatStudentCourseEnrollmentsByCourseScheduleId(String courseScheduleId) throws ApplicationException {
		LOGGER.info("Processing Get StudentCourseEnrollment Report Request ...; TX_ID ({}) courseScheduleId ({})", getTxId(), courseScheduleId);
		//throw EntityNotFound Exception if course schedule not found
		courseScheduleRepository.findOne(courseScheduleId);
		StudentCourseEnrollmentRepository repository = (StudentCourseEnrollmentRepository) studentCourseEnrollmentRepository.getRepository();
		Collection<StudentCourseEnrollmentReportModel> result = repository.findByCourseScheduleId(courseScheduleId).stream()
				.map( (studentCourseEnrollment) -> mapper.map(studentCourseEnrollment, StudentCourseEnrollmentReportModel.class))
				.collect(Collectors.toList());
		LOGGER.info("Processed Get StudentCourseEnrollment Report Request; TX_ID ({}) courseScheduleId ({}) size ({})", getTxId(), courseScheduleId, result.size());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public StudentCourseEnrollmentModel getStudentCourseEnrollment(String id) throws ApplicationException {
		LOGGER.info("Processing Get StudentCourseEnrollment By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		StudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentRepository.findOne(id);
		StudentCourseEnrollmentModel studentCourseEnrollmentModel = mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		LOGGER.info("Processed Get StudentCourseEnrollment By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return studentCourseEnrollmentModel;
	}
	
	public StudentCourseEnrollmentModel createStudentCourseEnrollment(StudentCourseEnrollmentModel studentCourseEnrollmentModel) throws ApplicationException {
		LOGGER.info("Processing Create StudentCourseEnrollment Request ...; TX_ID ({})", getTxId());
		StudentCourseEnrollment studentCourseEnrollment = mapper.map(studentCourseEnrollmentModel, StudentCourseEnrollment.class);
		studentCourseEnrollmentRepository.create(studentCourseEnrollment);
		StudentCourseEnrollmentModel createdStudentCourseEnrollmentModel = mapper.map(studentCourseEnrollment, StudentCourseEnrollmentModel.class);
		LOGGER.info("Processed Create StudentCourseEnrollment Request; TX_ID ({}) createdID ({})", getTxId(), studentCourseEnrollmentModel.getId());
		return createdStudentCourseEnrollmentModel;
	}
	
	public void updateStudentCourseEnrollment(StudentCourseEnrollmentModel studentCourseEnrollmentModel) throws ApplicationException {
		LOGGER.info("Processing Update StudentCourseEnrollment Request ...; TX_ID ({}) ID ({})", getTxId(), studentCourseEnrollmentModel.getId());
		StudentCourseEnrollment studentCourseEnrollment = mapper.map(studentCourseEnrollmentModel, StudentCourseEnrollment.class);
		studentCourseEnrollmentRepository.update(studentCourseEnrollment);
		LOGGER.info("Processed Update StudentCourseEnrollment Request; TX_ID ({}) ID ({})", getTxId(), studentCourseEnrollmentModel.getId());
	}
	
	public void deleteStudentCourseEnrollment(String id) throws ApplicationException {
		LOGGER.info("Processing Delete StudentCourseEnrollment Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		studentCourseEnrollmentRepository.delete(id);
		LOGGER.info("Processed Delete StudentCourseEnrollment Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
