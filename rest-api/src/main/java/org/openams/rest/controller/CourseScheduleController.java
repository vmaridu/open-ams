package org.openams.rest.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.model.CourseScheduleAttendanceReportModel;
import org.openams.rest.model.CourseScheduleModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentCourseEnrollmentReportModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.AttendanceService;
import org.openams.rest.service.impl.CourseScheduleService;
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

@Api(value = "Course Schedule Controller", description = "Allows CRUD on CourseSchedule")
@RestController
@RequestMapping("/course-schedules")
public class CourseScheduleController {

	private final CourseScheduleService courseScheduleService;
	private final StudentCourseEnrollmentService studentCourseEnrollmentService;
	private final AttendanceService attendanceService;

	@Autowired
	public CourseScheduleController(CourseScheduleService service, AttendanceService attendanceService,
			StudentCourseEnrollmentService studentCourseEnrollmentService) {
		this.courseScheduleService = service;
		this.attendanceService = attendanceService;
		this.studentCourseEnrollmentService = studentCourseEnrollmentService;
	}

	@ApiOperation(value = "Gets CourseSchedule Details by Filter ; Allowed Roles [ADMIN|STAFF]")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", paramType = "query"),
			@ApiImplicitParam(name = "limit", paramType = "query"),
			@ApiImplicitParam(name = "sort", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<CourseScheduleModel> getByFilter(@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable) throws QueryParserException {
		if (StringUtils.isBlank(filter)) {
			return courseScheduleService.getCourseSchedules(pageable);
		} else {
			return courseScheduleService.getCourseSchedules(pageable, filter);
		}
	}

	@ApiOperation(value = "Gets CourseSchedule Filter Config ; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/filter-config", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFilterConfig() {
		return courseScheduleService.getFilterConfig();
	}

	@ApiOperation(value = "Gets CourseSchedule By ID ; Allowed Roles [ADMIN|SELF]")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CourseScheduleModel getCourseSchedule(@PathVariable("id") String id) {
		return courseScheduleService.getCourseSchedule(id);
	}

	@ApiOperation(value = "Gets StudentCourseEnrollment Report for CourseSchedule ID; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/{id}/enrollment_report", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection<StudentCourseEnrollmentReportModel> getFlatStudentCourseEnrollmentsByCourseScheduleId(@PathVariable("id") String id) {
		return studentCourseEnrollmentService.getFlatStudentCourseEnrollmentsByCourseScheduleId(id);
	}

	@ApiOperation(value = "Gets Attendance Report for CourseSchedule ID ; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(path = "/{id}/attendance_report", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CourseScheduleAttendanceReportModel getAttendanceReport(@PathVariable("id") String id,
			@RequestParam(value = "fromDtt", required = false) String fromDttString,
			@RequestParam(value = "toDtt", required = false) String toDttString) throws ApplicationException {
		Date fromDtt = StringUtils.isEmpty(fromDttString) ? null : ConverterUtil.toDateTime(fromDttString);
		Date toDtt = StringUtils.isEmpty(toDttString) ? null : ConverterUtil.toDateTime(toDttString);
		return attendanceService.getCourseScheduleAttendanceReport(id, fromDtt, toDtt);
	}

	@ApiOperation(value = "Creates CourseSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid CourseScheduleModel courseSchedule, HttpServletResponse response) {
		CourseScheduleModel createdCourseSchedule = courseScheduleService.createCourseSchedule(courseSchedule);
		response.setHeader("Location", "/course-schedules/" + createdCourseSchedule.getId());
	}

	@ApiOperation(value = "Updates CourseSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") String id, @RequestBody @Valid CourseScheduleModel courseSchedule) {
		courseSchedule.setId(id);
		courseScheduleService.updateCourseSchedule(courseSchedule);
	}

	@ApiOperation(value = "Deletes CourseSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		courseScheduleService.deleteCourseSchedule(id);
	}

}
