package org.openams.rest.model;

import java.util.Collection;
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
	
	@ApiModelProperty(value = "Map of <enrollementId,attendanceCSVRecord>; attendanceCSVRecord = present;absent;onLeave;other", dataType = "Map")
	private Map<String,String> attendancesSummary;
	
	@ApiModelProperty(value = "Detailed Attendaace Data", dataType = "Collection")
	private Collection<AttendanceByModel> attendances;
	
	
	
}
