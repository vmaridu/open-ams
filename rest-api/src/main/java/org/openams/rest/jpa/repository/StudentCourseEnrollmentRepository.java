package org.openams.rest.jpa.repository;

import java.util.Collection;
import java.util.Set;

import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentCourseEnrollmentRepository extends BaseRepository<StudentCourseEnrollment,String> {

	@Query("SELECT s FROM StudentCourseEnrollment s WHERE s.courseSchedule.id = :courseScheduleId")
	public Collection<StudentCourseEnrollment> findByCourseScheduleId(@Param("courseScheduleId")  String courseScheduleId);
	
	@Query("SELECT s.id FROM StudentCourseEnrollment s WHERE s.courseSchedule.id = :courseScheduleId")
	public Set<String> findIdsByCourseScheduleId(@Param("courseScheduleId")  String courseScheduleId);
}
