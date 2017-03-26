package org.openams.rest.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Password")
public class PasswordModel {

	@ApiModelProperty(value = "Old Password", dataType = "String")
	@NotNull
	private String oldPassword;
	
	@ApiModelProperty(value = "New Password", dataType = "String")
	@NotNull
	private String newPassword;
	
}
