package org.openams.rest.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ValidationException;
import org.openams.rest.jpa.entity.Attendance;
import org.openams.rest.jpa.entity.AttendanceBy;
import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.openams.rest.jpa.entity.enums.AttendanceStatus;
import org.openams.rest.jpa.repository.AttendanceByRepository;
import org.openams.rest.jpa.repository.AttendanceRepository;
import org.openams.rest.jpa.repository.StudentCourseEnrollmentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.AttendanceByModel;
import org.openams.rest.model.AttendanceModel;
import org.openams.rest.model.EnrollmentAttendanceReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AttendanceService {

	private Mapper mapper;
	private AttendanceByRepository attendanceByRepository;
	private AttendanceRepository attendanceRepository;
	private StudentCourseEnrollmentRepository studentCourseEnrollmentRepository;
	
	@Autowired
	public AttendanceService(AttendanceByRepository attendanceByRepository, AttendanceRepository attendanceRepository, StudentCourseEnrollmentRepository studentCourseEnrollmentRepository , Mapper mapper) {
		this.mapper = mapper;
		this.attendanceByRepository = attendanceByRepository;
		this.attendanceRepository = attendanceRepository;
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
	
	//TODO:Offer Filter by Date Range
	public EnrollmentAttendanceReportModel getEnrollmentAttendanceReportModel(String enrollmentId, Date from, Date to){
		EnrollmentAttendanceReportModel report = new EnrollmentAttendanceReportModel();
		
		RepositoryWrapper<StudentCourseEnrollment, String> studentCourseEnrollmentRepositoryWrapper = new RepositoryWrapper<StudentCourseEnrollment, String>(studentCourseEnrollmentRepository, (StudentCourseEnrollment::getId));
		StudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentRepositoryWrapper.findOne(enrollmentId);
		report.setEnrollmentId(enrollmentId);
		report.setFName(studentCourseEnrollment.getStudent().getFName());
		report.setMName(studentCourseEnrollment.getStudent().getMName());
		report.setLName(studentCourseEnrollment.getStudent().getLName());
		report.setRollNumber(studentCourseEnrollment.getStudent().getRollNumber());
		report.setStudentId(studentCourseEnrollment.getStudent().getId());

		Collection<Attendance> attendances = attendanceRepository.findByStudentCourseEnrollmentId(enrollmentId);
		if(attendances != null){
			Map<AttendanceStatus,List<Attendance>> attendancesGroupedByStatus = attendances.stream().collect(Collectors.groupingBy(a -> a.getStatus()));
			List<Attendance> presentAttendances = attendancesGroupedByStatus.get(AttendanceStatus.PRESENT);
			List<Attendance> absentAttendances = attendancesGroupedByStatus.get(AttendanceStatus.ABSENT);
			List<Attendance> onLeaveAttendances = attendancesGroupedByStatus.get(AttendanceStatus.ON_LEAVE);
			List<Attendance> otherAttendances = attendancesGroupedByStatus.get(AttendanceStatus.OTHER);
			report.setPresent(presentAttendances == null ? 0 : presentAttendances.size());
			report.setAbsent(absentAttendances == null ? 0 : absentAttendances.size());
			report.setOnLeave(onLeaveAttendances == null ? 0 : onLeaveAttendances.size());
			report.setOther(otherAttendances == null ? 0 : otherAttendances.size());
			
			Collection<AttendanceModel> attendanceModels = attendances.stream().map(m -> mapper.map(m, AttendanceModel.class)).collect(Collectors.toList());
			report.setAttendances(attendanceModels);
		}
		
		return report;
	}
	
}
