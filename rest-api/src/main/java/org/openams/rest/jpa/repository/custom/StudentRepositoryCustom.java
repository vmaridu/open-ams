package org.openams.rest.jpa.repository.custom;

import org.openams.rest.jpa.entity.Student;

public interface StudentRepositoryCustom {

	public Student findStudentByRollNumber(String rollNumber);
}
