package org.openams.rest.model;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.openams.rest.jpa.entity.enums.UserStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User Account Data")
public class UserModel {

	@ApiModelProperty(value = "User Name", required = true, dataType = "String")
	@NotNull
	@Size(min = 1, max=255)
	private String userName;

	@ApiModelProperty(value = "E Mail; Password will be sent to this e-mail", dataType = "String")
	@NotNull
	@Size(min = 1, max=255)
	private String email;

	@ApiModelProperty(value = "User Account Status", required = true, dataType = "UserStatus")
	private UserStatus status;

	@ApiModelProperty(value = "Account Expire Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
	private Date accountExpireDtt;
	
	@ApiModelProperty(value = "Credentials Expire Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
	private Date credentialsExpireDtt;
	
	@ApiModelProperty(value = "Last Access Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
	private Date lastAccessDtt;
	
    @ApiModelProperty(value = "User Roles", required = true, dataType = "String")
	private Collection<String> roles;

}
