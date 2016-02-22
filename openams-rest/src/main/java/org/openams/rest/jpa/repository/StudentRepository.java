package org.openams.rest.jpa.repository;

import org.openams.rest.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String>{

	
}
