package org.openams.rest.jpa.repository;

import java.util.Collection;
import java.util.Date;

import org.openams.rest.jpa.entity.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository extends BaseRepository<Attendance,String>{

	@Query("SELECT a FROM Attendance a WHERE "
			+ "a.studentCourseEnrollment.id = :studentCourseEnrollmentId "
			+ "AND ( :fromDtt is NULL  OR  a.attendanceBy.takenDtt >= :fromDtt) "
			+ "AND ( :toDtt is NULL  OR  a.attendanceBy.takenDtt <= :toDtt) "
			+ "ORDER BY a.attendanceBy.takenDtt")
	public Collection<Attendance> findByStudentCourseEnrollmentId(
			@Param("studentCourseEnrollmentId")  String studentCourseEnrollmentId,
			@Param("fromDtt")  Date fromDtt, @Param("toDtt")  Date toDtt);
	
	
	@Query("SELECT a.status, count(a) FROM Attendance a "
			+ "WHERE a.studentCourseEnrollment.id = :studentCourseEnrollmentId "
			+ "AND ( :fromDtt is NULL  OR  a.attendanceBy.takenDtt >= :fromDtt) "
			+ "AND ( :toDtt is NULL  OR  a.attendanceBy.takenDtt <= :toDtt) "
			+ "GROUP BY a.status")
	public Collection<Object[]> findStatusToCountMapByStudentCourseEnrollmentId(
			@Param("studentCourseEnrollmentId")  String studentCourseEnrollmentId,
			@Param("fromDtt")  Date fromDtt, @Param("toDtt")  Date toDtt);
	
	
	@Query("SELECT a.studentCourseEnrollment, a.status, count(a.status) FROM Attendance a "
			+ "WHERE a.studentCourseEnrollment.courseSchedule.id = :courseScheduleId "
			+ "AND ( :fromDtt is NULL  OR  a.attendanceBy.takenDtt >= :fromDtt) "
			+ "AND ( :toDtt is NULL  OR  a.attendanceBy.takenDtt <= :toDtt) "
			+ "GROUP BY a.studentCourseEnrollment, a.status")
	public Collection<Object[]> findAttendanceSummaryByCourseScheduletId(
			@Param("courseScheduleId")  String courseScheduleId,
			@Param("fromDtt")  Date fromDtt, @Param("toDtt")  Date toDtt);
	
}
