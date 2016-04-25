package org.openams.rest.service;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentService extends BaseService<Student,String>  {

	private StudentRepository repository;

	@Autowired
	public StudentService(StudentRepository repository) {
		super(repository,Student::getId);
		this.repository = repository;
	}

}
