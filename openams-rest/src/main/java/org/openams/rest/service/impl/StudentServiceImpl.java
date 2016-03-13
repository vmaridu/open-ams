package org.openams.rest.service.impl;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.StudentRepository;
import org.openams.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,String> implements StudentService {

	private StudentRepository repository;

	@Autowired
	public StudentServiceImpl(StudentRepository repository) {
		super(repository,Student::getId);
		this.repository = repository;
	}

}
