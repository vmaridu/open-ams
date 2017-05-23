package org.openams.rest.service.impl;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.StudentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;

@Transactional
@Service
public class StudentService {

	private RepositoryWrapper<Student, String> repository;
	private Mapper mapper;

	@Autowired
	public StudentService(StudentRepository repository, Mapper mapper) {
		this.mapper = mapper;
		this.repository = new RepositoryWrapper<Student, String>(repository, (Student::getId));
	}

	public StudentModel getStudentByRollNumber(String rollNumber) {
		Path<String> propPath = Expressions.path(String.class, Expressions.path(Student.class, "student"), "rollNumber");
		Predicate predicate = Expressions.predicate(Ops.EQ, propPath, Expressions.constant(rollNumber));
		Student student = repository.findOne(predicate);
		StudentModel studentModel = mapper.map(student, StudentModel.class);
		return studentModel;
	}

}
