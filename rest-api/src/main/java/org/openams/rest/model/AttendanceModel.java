package org.openams.rest.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.openams.rest.jpa.entity.enums.AttendanceStatus;

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
}
