package org.openams.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Fee Details")
public class FeeModel {

	@ApiModelProperty(value = "ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Academic Term", dataType = "String")
	private String academicTerm;

	@ApiModelProperty(value = "Name or Label", dataType = "String")
	private String name;
	
	@ApiModelProperty(value = "Fee Amount", dataType = "Float")
	private float amount;

	@ApiModelProperty(value = "Comment", dataType = "String")
	private String comment;

	@ApiModelProperty(value = "Student Infomration; studentId;firstName;mName;lName", dataType = "String")
	private String studentInfo;
	
	
}
