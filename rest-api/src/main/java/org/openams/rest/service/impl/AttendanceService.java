package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.exception.ValidationException;
import org.openams.rest.jpa.entity.Attendance;
import org.openams.rest.jpa.entity.AttendanceBy;
import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.openams.rest.jpa.entity.enums.AttendanceStatus;
import org.openams.rest.jpa.repository.AttendanceByRepository;
import org.openams.rest.jpa.repository.AttendanceRepository;
import org.openams.rest.jpa.repository.CourseScheduleRepository;
import org.openams.rest.jpa.repository.StudentCourseEnrollmentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.AttendanceByModel;
import org.openams.rest.model.AttendanceModel;
import org.openams.rest.model.CourseScheduleAttendanceReportModel;
import org.openams.rest.model.EnrollmentAttendanceReportModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AttendanceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceService.class);

	private Mapper mapper;
	private AttendanceByRepository attendanceByRepository;
	private AttendanceRepository attendanceRepository;
	private RepositoryWrapper<CourseSchedule, String> courseScheduleRepository;
	private StudentCourseEnrollmentRepository studentCourseEnrollmentRepository;
	
	@Autowired
	public AttendanceService(AttendanceByRepository attendanceByRepository, AttendanceRepository attendanceRepository, 
			CourseScheduleRepository courseScheduleRepository,
			StudentCourseEnrollmentRepository studentCourseEnrollmentRepository , Mapper mapper) {
		this.mapper = mapper;
		this.attendanceByRepository = attendanceByRepository;
		this.attendanceRepository = attendanceRepository;
		this.studentCourseEnrollmentRepository = studentCourseEnrollmentRepository;
		this.courseScheduleRepository = new RepositoryWrapper<CourseSchedule, String>(courseScheduleRepository, (CourseSchedule::getId));
	}
	
	public void submitBulkAttendance(AttendanceByModel bulkAttendance) throws ApplicationException{
		String courseScheduleId = bulkAttendance.getCourseScheduleId();
    	LOGGER.info("Processing Bulk Attendance Request ...; TX_ID ({}) courseScheduleId ({}) ", getTxId(), courseScheduleId);
		Set<String> expectedEnrollmentIds = getEnrollmentIdsByCourseScheduleId(courseScheduleId);
		Set<String> actualEnrollmentIds = bulkAttendance.getAttendances().stream().map(AttendanceModel :: getEnrollmentId).collect(Collectors.toSet());
		if(actualEnrollmentIds.equals(expectedEnrollmentIds)){
			AttendanceBy attendanceBy = mapper.map(bulkAttendance, AttendanceBy.class);
			attendanceBy.getAttendances().forEach(a -> a.setAttendanceBy(attendanceBy));
			attendanceByRepository.save(attendanceBy);
			LOGGER.info("Processed Bulk Attendance Request; TX_ID ({}) courseScheduleId ({})", getTxId(), courseScheduleId);
		}else{
			LOGGER.error("Failed to Process Bulk Attendance Request : expectedEnrollmentIds doesn't match actualEnrollmentIds; TX_ID ({}) courseScheduleId ({})", getTxId(), courseScheduleId);
			throw new ValidationException("Attendance should be submited for all StudentEnrollmentIds of CourseSchedule");
		}
	}
	
	
	public CourseScheduleAttendanceReportModel getCourseScheduleAttendanceReport(String courseScheduleId, Date fromDtt, Date toDtt, boolean expand) throws ApplicationException {
		
		LOGGER.info("Processing CourseSchedue AttendanceReport Request ...; TX_ID ({}) courseScheduleId ({}) fromDtt ({}) toDtt ({}) expand ({})"
				, getTxId(), courseScheduleId, (fromDtt == null ? null : fromDtt.toString() ) , (toDtt == null ? null : toDtt.toString() ), expand);
		
		//throw exception if course schedule not found
		courseScheduleRepository.findOne(courseScheduleId);
		
		Collection<Object[]> attendanceSummaryRS = attendanceRepository.findAttendanceSummaryByCourseScheduletId(courseScheduleId, fromDtt, toDtt);
		Map<String,Long> attendanceSummaryRSMap = attendanceSummaryRS.stream().collect(Collectors.toMap(
				a -> (((StudentCourseEnrollment)a[0]).getId() + "_" + (((AttendanceStatus)a[1]).name())), a -> ((Long) a[2])));
		
		Map<String,String> attendanceSummary = getEnrollmentIdsByCourseScheduleId(courseScheduleId).stream().collect(Collectors.toMap(id -> id, id -> {
			long presentCount = attendanceSummaryRSMap.getOrDefault(id + "_PRESENT" , 0l);
			long absentCount = attendanceSummaryRSMap.getOrDefault(id + "_ABSENT" , 0l);
			long onLeaveCount = attendanceSummaryRSMap.getOrDefault(id + "_ON_LEAVE" , 0l);
			long otherCount = attendanceSummaryRSMap.getOrDefault(id + "_OTHER" , 0l);
			return presentCount + ";" + absentCount + ";" + onLeaveCount + ";" + otherCount;
		}));
		
		long totalPresentCount = attendanceSummaryRSMap.entrySet().stream().filter(e -> e.getKey().contains("PRESENT")).mapToLong(e -> e.getValue()).sum();
		long noOfClassesTaken = attendanceSummary.entrySet().stream().findFirst().map(e -> Arrays.stream(e.getValue().split(";")).mapToLong(Long :: parseLong).sum()).orElse(0l);
		
		CourseScheduleAttendanceReportModel result = new CourseScheduleAttendanceReportModel();
		result.setClassAverage((totalPresentCount * 100)/(noOfClassesTaken * attendanceSummary.size() * 1f));
		result.setTotalClasses(noOfClassesTaken);
		result.setAttendancesSummary(attendanceSummary);
		
		if(expand){
			Collection<AttendanceBy> attendanceBys = attendanceByRepository.findByCourseScheduleId(courseScheduleId, fromDtt, toDtt);
			Collection<AttendanceByModel> attendances = attendanceBys.stream()
					.map(a -> mapper.map(a, AttendanceByModel.class, "courseScheduleAttendanceByReport")).collect(Collectors.toList());
			result.setAttendances(attendances);
		}
		
		LOGGER.info("Processed CourseSchedue AttendanceReport Request ; TX_ID ({}) courseScheduleId ({}) fromDtt ({}) toDtt ({}) expand ({})"
				, getTxId(), courseScheduleId, (fromDtt == null ? null : fromDtt.toString() ) , (toDtt == null ? null : toDtt.toString() ), expand);
		
		return result;
	}
	
	
	public Set<String> getEnrollmentIdsByCourseScheduleId(String courseScheduleId) throws ApplicationException {
		return studentCourseEnrollmentRepository.findIdsByCourseScheduleId(courseScheduleId);
	}
	
	public EnrollmentAttendanceReportModel getEnrollmentAttendanceReportModel(String enrollmentId, Date fromDtt, Date toDtt, boolean expand) throws ApplicationException{
		
		LOGGER.info("Processing Enrollment AttendanceReport Request ...; TX_ID ({}) enrollmentId ({}) fromDtt ({}) toDtt ({}) expand ({})"
				, getTxId(), enrollmentId, (fromDtt == null ? null : fromDtt.toString() ) , (toDtt == null ? null : toDtt.toString() ), expand);
		
		EnrollmentAttendanceReportModel report = new EnrollmentAttendanceReportModel();
		
		RepositoryWrapper<StudentCourseEnrollment, String> studentCourseEnrollmentRepositoryWrapper = new RepositoryWrapper<StudentCourseEnrollment, String>(studentCourseEnrollmentRepository, (StudentCourseEnrollment::getId));
		StudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentRepositoryWrapper.findOne(enrollmentId);
		report.setEnrollmentId(enrollmentId);
		report.setFName(studentCourseEnrollment.getStudent().getFName());
		report.setMName(studentCourseEnrollment.getStudent().getMName());
		report.setLName(studentCourseEnrollment.getStudent().getLName());
		report.setRollNumber(studentCourseEnrollment.getStudent().getRollNumber());
		report.setStudentId(studentCourseEnrollment.getStudent().getId());

		Collection<Object[]> attendanceStatusCounts = attendanceRepository.findStatusToCountMapByStudentCourseEnrollmentId(enrollmentId, fromDtt, toDtt);
		Map<AttendanceStatus,Integer> attendancesStatusToCountMap = attendanceStatusCounts.stream().collect(Collectors.toMap(i -> ((AttendanceStatus)i[0]), i -> ((Long)i[1]).intValue()));
		report.setPresent(attendancesStatusToCountMap.getOrDefault(AttendanceStatus.PRESENT, 0));
		report.setAbsent(attendancesStatusToCountMap.getOrDefault(AttendanceStatus.ABSENT, 0));
		report.setOnLeave(attendancesStatusToCountMap.getOrDefault(AttendanceStatus.ON_LEAVE, 0));
		report.setOther(attendancesStatusToCountMap.getOrDefault(AttendanceStatus.OTHER, 0));
		
		if(expand){
			Collection<Attendance> attendances = attendanceRepository.findByStudentCourseEnrollmentId(enrollmentId,fromDtt,toDtt);
			Collection<AttendanceModel> attendanceModels = attendances.stream().map(m -> mapper.map(m, AttendanceModel.class)).collect(Collectors.toList());
			report.setAttendances(attendanceModels);
		}
		
		LOGGER.info("Processed Enrollment AttendanceReport Request ...; TX_ID ({}) enrollmentId ({}) fromDtt ({}) toDtt ({}) expand ({})"
				, getTxId(), enrollmentId, (fromDtt == null ? null : fromDtt.toString() ) , (toDtt == null ? null : toDtt.toString() ), expand);
		
		return report;
	}
	
}
