package org.openams.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Student")
public class StudentModel extends PersonModel{

	@ApiModelProperty(value = "Course/Grade Level; Ex : K10,K12,1,2", dataType = "String")
	private String level;
	
	@ApiModelProperty(value = "Parent e-mail Address", dataType = "String")
	private String parentEmail;
	
	@ApiModelProperty(value = "Roll Number", dataType = "String")
	private String rollNumber;
}
