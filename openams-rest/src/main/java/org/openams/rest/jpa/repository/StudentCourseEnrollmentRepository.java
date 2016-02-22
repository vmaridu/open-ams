package org.openams.rest.jpa.repository;

import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment,Integer>{
	
}
