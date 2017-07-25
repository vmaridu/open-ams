package org.openams.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Test Score Details")
public class TestScoreModel {

	@ApiModelProperty(value = "Test ID", dataType = "String")
	private String id;
	
	@ApiModelProperty(value = "Notes", dataType = "String")
	private String notes;
	
	@ApiModelProperty(value = "Score; ", dataType = "String")
	private Float score;
	
	@ApiModelProperty(value = "Grade", dataType = "String")
	private String grade;
	
	@ApiModelProperty(value = "Student ID", dataType = "String")
	private String studentId;
	
	@ApiModelProperty(value = "Student Infomration; firstName;mName;lName", dataType = "String")
	private String studentInfo;
	
	@ApiModelProperty(value = "Test ID", dataType = "String")
	private String testId;

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
