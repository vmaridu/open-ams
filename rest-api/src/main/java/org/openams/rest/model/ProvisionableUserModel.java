package org.openams.rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User Account with Password")
public class ProvisionableUserModel extends UserModel{

	@ApiModelProperty(value = "Raw Password", required = true, dataType = "String")
	@NotNull
	@Size(min = 6, max=255)
	private String password;


}
