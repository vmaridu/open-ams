package org.openams.rest.model;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@ApiModelProperty(value = "Student ID;firstName;mName;lName", dataType = "String")
	private String studentId;
	
	@ApiModelProperty(value = "Student Infomration; firstName;mName;lName", dataType = "String")
	private String studentInfo;
	
	@ApiModelProperty(value = "Modified DateTime", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
	@ApiModelProperty(value = "Test Scores", dataType = "Collection<TestScore>")
	private Collection<PaymentModel> payments;
	
}
