package org.openams.rest.service.impl;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.StudentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StudentService   {

	private RepositoryWrapper<Student,String> repository;
	private Mapper mapper;

	@Autowired
	public StudentService(StudentRepository repository, Mapper mapper) {
		this.mapper = mapper;
		this.repository = new RepositoryWrapper<Student,String>(repository, (Student :: getId));
	}
	
	

	
	
}
