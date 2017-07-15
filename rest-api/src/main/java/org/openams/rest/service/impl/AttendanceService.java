package org.openams.rest.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ValidationException;
import org.openams.rest.jpa.entity.AttendanceBy;
import org.openams.rest.jpa.repository.AttendanceByRepository;
import org.openams.rest.jpa.repository.StudentCourseEnrollmentRepository;
import org.openams.rest.model.AttendanceByModel;
import org.openams.rest.model.AttendanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AttendanceService {

	private Mapper mapper;
	private AttendanceByRepository attendanceByRepository;
	private StudentCourseEnrollmentRepository studentCourseEnrollmentRepository;
	
	@Autowired
	public AttendanceService(AttendanceByRepository attendanceByRepository, StudentCourseEnrollmentRepository studentCourseEnrollmentRepository , Mapper mapper) {
		this.mapper = mapper;
		this.attendanceByRepository = attendanceByRepository;
		this.studentCourseEnrollmentRepository = studentCourseEnrollmentRepository;
	}
	
	public void submitBulkAttendance(AttendanceByModel bulkAttendance) throws ValidationException{
		String courseScheduleId = bulkAttendance.getCourseScheduleId();
		Set<String> expectedEnrollmentIds = getEnrollmentIdsByCourseScheduleId(courseScheduleId);
		Set<String> actualEnrollmentIds = bulkAttendance.getAttendances().stream().map(AttendanceModel :: getEnrollmentId).collect(Collectors.toSet());
		if(actualEnrollmentIds.equals(expectedEnrollmentIds)){
			AttendanceBy attendanceBy = mapper.map(bulkAttendance, AttendanceBy.class);
			attendanceBy.getAttendances().forEach(a -> a.setAttendanceBy(attendanceBy));
			attendanceByRepository.save(attendanceBy);
		}else{
			throw new ValidationException("Attendance should be submited for all StudentEnrollmentIds of CourseSchedule");
		}
	}
	
	public Set<String> getEnrollmentIdsByCourseScheduleId(String courseScheduleId){
		return studentCourseEnrollmentRepository.findIdsByCourseScheduleId(courseScheduleId);
	}
	
}
