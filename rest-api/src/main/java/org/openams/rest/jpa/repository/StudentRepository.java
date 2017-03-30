package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,String>{

	@Query("SELECT s FROM Student s WHERE s.user.userName = :userName")
	public Collection<Student> findByUserName(String userName);

	

}
