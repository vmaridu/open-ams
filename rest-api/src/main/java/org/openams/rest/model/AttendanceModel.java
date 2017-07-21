package org.openams.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.openams.rest.jpa.entity.enums.AttendanceStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Attendance Details")
public class AttendanceModel {

	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Enrollment ID", dataType = "String")
	private String enrollmentId;
	
	@NotNull
	@ApiModelProperty(value = "Status, Ex : PRESENT,ABSENT,ON_LEAVE,OTHER", dataType = "String")
	private AttendanceStatus status;
	
	@ApiModelProperty(value = "Comment", dataType = "String")
	private String comment;
	
	@ApiModelProperty(value = "Taken Date Time", dataType = "Date Time")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date takenDtt;
	
}
