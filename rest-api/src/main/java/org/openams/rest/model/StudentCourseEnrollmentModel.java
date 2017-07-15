package org.openams.rest.model;

import java.util.Date;

import org.openams.rest.jpa.entity.enums.StudentCourseEnrollmentStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Student Course Enrollment Details")
public class StudentCourseEnrollmentModel {

	@ApiModelProperty(value = "Student Course Enrollment ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Student Grade : A, A+ etc ", dataType = "String")
	private String grade;
	
	@ApiModelProperty(value = "Enrollment Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date enrolledDtt;
	
	@ApiModelProperty(value = "Graded Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date gradedDtt;
	
	@ApiModelProperty(value = "Description", dataType = "String")
	private String desc;
	
	@ApiModelProperty(value = "Modified Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;

	@ApiModelProperty(value = "Status : INACTIVE, ACTIVE, GRADED", dataType = "StudentCourseEnrollmentStatus")
	private StudentCourseEnrollmentStatus status;
	
	@ApiModelProperty(value = "Course Schedule Id", dataType = "String")
	private String courseScheduleId;
	
	@ApiModelProperty(value = "Student Id", dataType = "String")
	private String studentId;
	
}
