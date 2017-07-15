package org.openams.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Student Course Enrollment Flat Model")
public class FlatStudentCourseEnrollmentModel {

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

}
