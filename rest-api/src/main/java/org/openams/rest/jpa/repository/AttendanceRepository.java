package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository extends BaseRepository<Attendance,String>{

	@Query("SELECT a FROM Attendance a WHERE a.studentCourseEnrollment.id = :studentCourseEnrollmentId ORDER BY a.attendanceBy.takenDtt")
	public Collection<Attendance> findByStudentCourseEnrollmentId(@Param("studentCourseEnrollmentId")  String studentCourseEnrollmentId);
	
}
