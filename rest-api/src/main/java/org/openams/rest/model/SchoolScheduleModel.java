package org.openams.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "School Schedule")
public class SchoolScheduleModel {

	@ApiModelProperty(value = "ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Event Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String eventName;
	
	@ApiModelProperty(value = "Start Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date startDtt;
	
	@ApiModelProperty(value = "End  Date Time", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date endDtt;
	
	@ApiModelProperty(value = "Status", dataType = "Byte")
	private Byte status;
	
	@ApiModelProperty(value = "Creator Staff Id", dataType = "String")
	private String staffId;
	
	@ApiModelProperty(value = "Modified Date", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
}
