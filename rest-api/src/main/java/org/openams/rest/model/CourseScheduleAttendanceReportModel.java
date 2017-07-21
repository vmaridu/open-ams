package org.openams.rest.model;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Attendance Report for Course Schedule")
public class CourseScheduleAttendanceReportModel {

	@ApiModelProperty(value = "Total number of Classes ", dataType = "String")
	private Long totalClasses;
	
	@ApiModelProperty(value = "Attendace Average Percentage (totalNumberOfClassAttended/(noOfStudentsPerClass * noOfClassPerCourseSchedule))", dataType = "Float")
	private Float classAverage;
	
	@ApiModelProperty(value = "Map of <enrollementId,attendanceCSVRecord>; attendanceCSVRecord = present;absent;onLeave;other", dataType = "String")
	private Map<String,String> attendances;
	
}
