package org.openams.rest.jpa.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.openams.rest.jpa.entity.QUser;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.custom.StudentRepositoryCustom;

import com.querydsl.core.types.dsl.Expressions;

public class StudentRepositoryImpl implements StudentRepositoryCustom{

	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;
	
	@Override
	public Student findStudentByRollNumber(String rollNumber) {
		
		return null;
	}

}
