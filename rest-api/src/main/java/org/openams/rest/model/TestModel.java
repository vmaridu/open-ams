package org.openams.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Test Details")
public class TestModel {

	@ApiModelProperty(value = "Test ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "Description", dataType = "String")
	private String desc;
	
	@ApiModelProperty(value = "Reference ID , Can be CourseSchedule or Staff or Any UUID", dataType = "DateTime")
	private String refId;
	
	@ApiModelProperty(value = "MAX Score; ", dataType = "String")
	private Float maxScore;
	
	@ApiModelProperty(value = "MAX Grade; ", dataType = "String")
	private String maxGrade;
	
	@ApiModelProperty(value = "Test Type , Ex : Online,Internal,Unit,Quarterly", dataType = "String")
	private String testType;
	
	@ApiModelProperty(value = "Test Start DateTime", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date startDtt;
	
	@ApiModelProperty(value = "Test Start DateTime", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date endDtt;
	
	@ApiModelProperty(value = "Modified DateTime", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;

	
}
