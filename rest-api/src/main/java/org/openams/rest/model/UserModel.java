package org.openams.rest.model;

import java.util.Collection;

import org.openams.rest.jpa.entity.enums.UserStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User Account Data")
public class UserModel {

	@ApiModelProperty(value = "User Name", required = true, dataType = "String")
	private String userName;

	@ApiModelProperty(value = "E Mail", dataType = "String")
	private String email;

	@ApiModelProperty(value = "User Account Status", required = true, dataType = "UserStatus")
	private UserStatus status;

    @ApiModelProperty(value = "User Roles", required = true, dataType = "String")
	private Collection<String> roles;

}
