package org.openams.rest.service.impl;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.QStudent;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.StudentRepository;
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

	private RepositoryWrapper<Student, String> repository;
	private Mapper mapper;
	private StudentQueryParser parser;

	@Autowired
	public StudentService(StudentRepository repository, Mapper mapper, StudentQueryParser parser) {
		this.mapper = mapper;
		this.repository = new RepositoryWrapper<Student, String>(repository, (Student::getId));;
		this.parser = parser;
	}

	public Page<StudentModel> getStudents(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Student> students = repository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Student, StudentModel> contentMapper = (student) -> mapper.map(student, StudentModel.class);
		return PaginationUtil.toPageModel(students, pageRequest, contentMapper);
	}
	
	public StudentModel getStudentByRollNumber(String rollNumber) {
		Student student = repository.findOne(QStudent.student.rollNumber.eq(rollNumber));
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		return studentModel;
	}

}
