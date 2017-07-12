package org.openams.rest.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Contact Details")
public class ContactModel {

	@ApiModelProperty(value = "Contact ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Address Line 1", dataType = "String")
	@NotNull
	@NotEmpty
	private String addressLine1;

	@ApiModelProperty(value = "Address Line 2", dataType = "String")
	@NotNull
	@NotEmpty
	private String addressLine2;

	@ApiModelProperty(value = "Apartment", dataType = "String")
	private String apartment;

	@ApiModelProperty(value = "City", dataType = "String")
	@NotNull
	@NotEmpty
	private String city;

	@ApiModelProperty(value = "Country Code", dataType = "String")
	@NotNull
	@NotEmpty
	private String country;

	@ApiModelProperty(value = "e-mail", dataType = "String")
	private String eMail;

	@ApiModelProperty(value = "Home Phone", dataType = "String")
	private String homePhone;

	@ApiModelProperty(value = "Contact Name/Label", dataType = "String")
	@NotNull
	@NotEmpty
	private String name;

	@ApiModelProperty(value = "Contact Notes", dataType = "String")
	private String notes;

	@ApiModelProperty(value = "Contact Primmary Phone", dataType = "String")
	private String phone;

	@ApiModelProperty(value = "State", dataType = "String")
	@NotNull
	@NotEmpty
	private String state;

	@ApiModelProperty(value = "Street Name", dataType = "String")
	private String street;

	@ApiModelProperty(value = "Zip Code", dataType = "String")
	@NotNull
	@NotEmpty
	private int zip;
}
