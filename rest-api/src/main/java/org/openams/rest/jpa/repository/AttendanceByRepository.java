package org.openams.rest.jpa.repository;

import java.util.Collection;
import java.util.Date;

import org.openams.rest.jpa.entity.AttendanceBy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceByRepository extends BaseRepository<AttendanceBy,String>{

	@Query("SELECT a FROM AttendanceBy a WHERE "
			+ "a.courseSchedule.id = :courseScheduleId "
			+ "AND ( :fromDtt is NULL  OR  a.takenDtt >= :fromDtt) "
			+ "AND ( :toDtt is NULL    OR  a.takenDtt <= :toDtt) "
			+ "ORDER BY a.takenDtt")
	public Collection<AttendanceBy> findByCourseScheduleId(
			@Param("courseScheduleId")  String courseScheduleId,
			@Param("fromDtt")  Date fromDtt, @Param("toDtt")  Date toDtt);
}
