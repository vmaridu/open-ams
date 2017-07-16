package org.openams.rest.model;

import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Attendance Report for Course Schedule")
public class CourseScheduleAttendanceReportModel {

	@ApiModelProperty(value = "Total number of Classes ", dataType = "String")
	private Integer totalClasses;
	
	@ApiModelProperty(value = "Attendace Average Percentage (totalNumberOfClassAttended/(noOfStudentsPerClass * noOfClassPerCourseSchedule))", dataType = "Float")
	private Float presentPercentage;
	
	@ApiModelProperty(value = "Collection of (Attendance Report for Student Course Enrollment)", dataType = "String")
	private Collection<EnrollmentAttendanceReportModel> enrollmentAttendaces;
}
