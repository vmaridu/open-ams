package org.openams.rest.service.impl;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.QStudent;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.StudentRepository;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.queryparser.StudentQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentService {

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
	
	public Page<StudentModel> getStudents(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<Student> students = studentRepository.findAll(pageRequest);
		Function<Student, StudentModel> contentMapper = (student) -> mapper.map(student, StudentModel.class);
		return PaginationUtil.toPageModel(students, pageRequest, contentMapper);
	}

	public Page<StudentModel> getStudents(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Student> students = studentRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Student, StudentModel> contentMapper = (student) -> mapper.map(student, StudentModel.class);
		return PaginationUtil.toPageModel(students, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public StudentModel getStudentByRollNumber(String rollNumber) {
		Student student = studentRepository.findOne(QStudent.student.rollNumber.eq(rollNumber));
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		return studentModel;
	}
	
	public StudentModel getStudent(String id) {
		Student student = studentRepository.findOne(id);
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		return studentModel;
	}
	
	public StudentModel createStudent(StudentModel studentModel){
		Student student = mapper.map(studentModel, Student.class);
		studentRepository.create(student);
		StudentModel createdStudentModel = mapper.map(student, StudentModel.class);
		return createdStudentModel;
	}
	
	public void updateStudent(StudentModel studentModel){
		Student student = mapper.map(studentModel, Student.class);
		Student existingStudent = studentRepository.findOne(student.getId());
		student.setUser(existingStudent.getUser());
		studentRepository.update(student);
	}
	
	public void deleteStudent(String id){
		studentRepository.delete(id);
	}
	
	public void linkStudentWithUser(String studentId, String userName){
		Student student = studentRepository.findOne(studentId);
		User user = userRepository.findOne(userName);
		student.setUser(user);
		studentRepository.update(student);
	}
}
