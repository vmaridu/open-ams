package org.openams.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Staff")
public class StaffModel extends PersonModel{

	@ApiModelProperty(value = "Alternative ID", dataType = "String")
	private String altId;

	@ApiModelProperty(value = "Description", dataType = "String")
	private String desc;

	@ApiModelProperty(value = "designation", dataType = "String")
	private String designation;
}
