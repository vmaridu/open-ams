package org.openams.rest.model;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.openams.rest.jpa.entity.enums.CourseScheduleStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Course Schedule Details")
public class CourseScheduleModel {

	@ApiModelProperty(value = "Course Schedule ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "Description", dataType = "String")
	private String desc;
	
	@ApiModelProperty(value = "Status", dataType = "CourseScheduleStatus")
	private CourseScheduleStatus status;
	
	@ApiModelProperty(value = "Class Location , Ex : ROOM 342", dataType = "String")
	private String location;
	
	@ApiModelProperty(value = "Class Start Date", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDt;
	
	@ApiModelProperty(value = "Class End Date", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDt;

	@ApiModelProperty(value = "Class Start Time", dataType = "Time")
	private Time startT;
	
	@ApiModelProperty(value = "Class End Time", dataType = "Time")
	private Time endT;
	
	@ApiModelProperty(value = "Term; Ex : 2016 FALL, 3-2 Semister", dataType = "String")
	private String term;
	
	@ApiModelProperty(value = "Instructor Staff Id", dataType = "String")
	private String instructorId;
	
	@ApiModelProperty(value = "Course ID", dataType = "String")
	private String courseId;
	
	@ApiModelProperty(value = "Modified Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
}
