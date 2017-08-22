package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.QStudent;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.StudentRepository;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentModel;
import org.openams.rest.queryparser.StudentQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	
	private RepositoryWrapper<Student, String> studentRepository;
	private RepositoryWrapper<User, String> userRepository;
	private Mapper mapper;
	private StudentQueryParser parser;

	@Autowired
	public StudentService(StudentRepository studentRepository, UserRepository userRepository, Mapper mapper, StudentQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.studentRepository = new RepositoryWrapper<Student, String>(studentRepository, (Student::getId));
		this.userRepository  = new RepositoryWrapper<User, String>(userRepository, (User::getUserName));
	}
	
	public Page<StudentModel> getStudents(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get Students Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Student> students = studentRepository.findAll(pageRequest);
		Function<Student, StudentModel> contentMapper = (student) -> mapper.map(student, StudentModel.class);
		Page<StudentModel> result = PaginationUtil.toPageModel(students, pageRequest, contentMapper);
		LOGGER.info("Processed Get Students Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<StudentModel> getStudents(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get Students Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Student> students = studentRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Student, StudentModel> contentMapper = (student) -> mapper.map(student, StudentModel.class);
		Page<StudentModel> result = PaginationUtil.toPageModel(students, pageRequest, contentMapper);
		LOGGER.info("Processed Get Students Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public StudentModel getStudentByRollNumber(String rollNumber) throws ApplicationException {
		LOGGER.info("Processing Get Student By RollNumber Request ...; TX_ID ({}) rollNumber ({})", getTxId(), rollNumber);
		Student student = studentRepository.findOne(QStudent.student.rollNumber.eq(rollNumber));
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		LOGGER.info("Processed Get Student By RollNumber Request; TX_ID ({}) rollNumber ({})", getTxId(), rollNumber);
		return studentModel;
	}
	
	public StudentModel getStudent(String id) throws ApplicationException {
		LOGGER.info("Processing Get Student By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		Student student = studentRepository.findOne(id);
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		LOGGER.info("Processed Get Student By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return studentModel;
	}
	
	public StudentModel createStudent(StudentModel studentModel) throws ApplicationException {
		LOGGER.info("Processing Create Student Request ...; TX_ID ({})", getTxId());
		Student student = mapper.map(studentModel, Student.class);
		studentRepository.create(student);
		StudentModel createdStudentModel = mapper.map(student, StudentModel.class);
		LOGGER.info("Processed Create Student Request; TX_ID ({}) createdID ({})", getTxId(), studentModel.getId());
		return createdStudentModel;
	}
	
	public void updateStudent(StudentModel studentModel) throws ApplicationException {
		LOGGER.info("Processing Update Student Request ...; TX_ID ({}) ID ({})", getTxId(), studentModel.getId());
		Student student = mapper.map(studentModel, Student.class);
		Student existingStudent = studentRepository.findOne(student.getId());
		student.setUser(existingStudent.getUser());
		studentRepository.update(student);
		LOGGER.info("Processed Update Student Request; TX_ID ({}) ID ({})", getTxId(), studentModel.getId());
	}
	
	public void deleteStudent(String id) throws ApplicationException {
		LOGGER.info("Processing Delete Student Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		studentRepository.delete(id);
		LOGGER.info("Processed Delete Student Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
	public void linkStudentWithUser(String studentId, String userName) throws ApplicationException {
		LOGGER.info("Processing Link Student with User Request ...; TX_ID ({}) staffId ({}) userName ({})", getTxId(), studentId, userName);
		Student student = studentRepository.findOne(studentId);
		User user = userRepository.findOne(userName);
		student.setUser(user);
		studentRepository.update(student);
		LOGGER.info("Processed Link Student with User Request; TX_ID ({}) staffId ({}) userName ({})", getTxId(), studentId, userName);
	}
}
