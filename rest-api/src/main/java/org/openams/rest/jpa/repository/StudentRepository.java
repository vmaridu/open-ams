package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.repository.custom.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends BaseRepository<Student,String>, StudentRepositoryCustom{

	@Query("SELECT s FROM Student s WHERE s.user.userName = :userName")
	public Collection<Student> findByUserName(String userName);

}
