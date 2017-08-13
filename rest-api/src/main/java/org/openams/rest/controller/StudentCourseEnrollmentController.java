package org.openams.rest.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.EnrollmentAttendanceReportModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentCourseEnrollmentModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.AttendanceService;
import org.openams.rest.service.impl.StudentCourseEnrollmentService;
import org.openams.rest.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Student Course Enrollments Controller", description = "Allows CRUD on StudentCourseEnrollment, Provides Enrollment and Attendance Reports")
@RestController
@RequestMapping("/api/student-course-enrollments")
public class StudentCourseEnrollmentController {

	private final StudentCourseEnrollmentService studentCourseEnrollmentService;
	private final AttendanceService attendanceService;

	@Autowired
	public StudentCourseEnrollmentController(StudentCourseEnrollmentService studentCourseEnrollmentService,
			AttendanceService attendanceService) {
		this.studentCourseEnrollmentService = studentCourseEnrollmentService;
		this.attendanceService = attendanceService;
	}

	@ApiOperation(value = "Gets StudentCourseEnrollment Details by Filter ; Allowed Roles [ADMIN|STAFF]")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", paramType = "query"),
			@ApiImplicitParam(name = "limit", paramType = "query"),
			@ApiImplicitParam(name = "sort", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<StudentCourseEnrollmentModel> getByFilter(
			@RequestParam(value = "filter", required = false) String filter, Pageable pageable)
			throws QueryParserException {
		if (StringUtils.isBlank(filter)) {
			return studentCourseEnrollmentService.getStudentCourseEnrollments(pageable);
		} else {
			return studentCourseEnrollmentService.getStudentCourseEnrollments(pageable, filter);
		}
	}

	@ApiOperation(value = "Gets Attendance Report for Student Course Enrollment ; Allowed Roles [ADMIN|STAFF|SELF]")
	@RequestMapping(value = "/{enrollmentId}/attendance_report", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public EnrollmentAttendanceReportModel getAttendanceReport(@PathVariable("enrollmentId") String enrollmentId,
			@RequestParam(value = "fromDtt", required = false) String fromDttString,
			@RequestParam(value = "toDtt", required = false) String toDttString,
			@RequestParam(value = "expand", defaultValue = "false") boolean expand) {
		Date fromDtt = StringUtils.isEmpty(fromDttString) ? null : ConverterUtil.toDateTime(fromDttString);
		Date toDtt = StringUtils.isEmpty(toDttString) ? null : ConverterUtil.toDateTime(toDttString);
		return attendanceService.getEnrollmentAttendanceReportModel(enrollmentId, fromDtt, toDtt, expand);
	}

	@ApiOperation(value = "Gets StudentCourseEnrollment Filter Config ; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/filter-config", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFilterConfig() {
		return studentCourseEnrollmentService.getFilterConfig();
	}

	@ApiOperation(value = "Gets StudentCourseEnrollment By ID ; Allowed Roles [ADMIN|SELF]")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public StudentCourseEnrollmentModel getStudentCourseEnrollment(@PathVariable("id") String id) {
		return studentCourseEnrollmentService.getStudentCourseEnrollment(id);
	}

	@ApiOperation(value = "Creates StudentCourseEnrollment; Allowed Roles [ADMIN]")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid StudentCourseEnrollmentModel studentCourseEnrollment,
			HttpServletResponse response) {
		StudentCourseEnrollmentModel createdStudentCourseEnrollment = studentCourseEnrollmentService
				.createStudentCourseEnrollment(studentCourseEnrollment);
		response.setHeader("Location", "/api/student-course-enrollments/" + createdStudentCourseEnrollment.getId());
	}

	@ApiOperation(value = "Updates StudentCourseEnrollment; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") String id,
			@RequestBody @Valid StudentCourseEnrollmentModel studentCourseEnrollment) {
		studentCourseEnrollment.setId(id);
		studentCourseEnrollmentService.updateStudentCourseEnrollment(studentCourseEnrollment);
	}

	@ApiOperation(value = "Deletes StudentCourseEnrollment; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		studentCourseEnrollmentService.deleteStudentCourseEnrollment(id);
	}

}
