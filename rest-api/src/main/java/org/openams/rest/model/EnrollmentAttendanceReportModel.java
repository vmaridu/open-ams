package org.openams.rest.model;

import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Attendance Report for Student Course Enrollment")
public class EnrollmentAttendanceReportModel {

	@ApiModelProperty(value = "Student Course Enrollment ID", dataType = "String")
	private String enrollmentId;
	
	@ApiModelProperty(value = "Student Id", dataType = "String")
	private String studentId;
	
	@ApiModelProperty(value = "Student Roll Number", dataType = "String")
	private String rollNumber;
	
	@ApiModelProperty(value = "Student First Name", dataType = "String")
	private String fName;

	@ApiModelProperty(value = "Student Middle Name", dataType = "String")
	private String mName;

	@ApiModelProperty(value = "Student Last Name", dataType = "String")
	private String lName;
	
	@ApiModelProperty(value = "Number of classes with PRESENT status", dataType = "Integer")
	private Integer present;
	
	@ApiModelProperty(value = "Number of class with ABSENT status", dataType = "Integer")
	private Integer absent;
	
	@ApiModelProperty(value = "Number of class with ON_LEAVE status", dataType = "Integer")
	private Integer onLeave;
	
	@ApiModelProperty(value = "Number of class with OTHER status", dataType = "String")
	private Integer other;

	@ApiModelProperty(value = "Attendace data order by Taken Date", dataType = "String")
	Collection<AttendanceModel> attendances;

}
