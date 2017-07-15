package org.openams.rest.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Attendance By Details")
public class AttendanceByModel {
	
	@ApiModelProperty(value = "Taken Date Time", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date takenDtt;
	
	@ApiModelProperty(value = "Comment", dataType = "String")
	private String comment;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Course Schedule Id", dataType = "String")
	private String courseScheduleId;

	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Taken By Staff Id", dataType = "String")
	private String takenByStaffId;
	
	@ApiModelProperty(value = "Attendances", dataType = "AttendanceModel")
	private List<AttendanceModel> attendances;
}
