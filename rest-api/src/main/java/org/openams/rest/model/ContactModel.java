package org.openams.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Contact Details")
public class ContactModel {

	@ApiModelProperty(value = "Contact ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Address Line 1", dataType = "String")
	private String addressLine1;

	@ApiModelProperty(value = "Address Line 2", dataType = "String")
	private String addressLine2;

	@ApiModelProperty(value = "Apartment", dataType = "String")
	private String apartment;

	@ApiModelProperty(value = "City", dataType = "String")
	private String city;

	@ApiModelProperty(value = "Country Code", dataType = "String")
	private String country;

	@ApiModelProperty(value = "e-mail", dataType = "String")
	private String eMail;

	@ApiModelProperty(value = "Home Phone", dataType = "String")
	private String homePhone;

	@ApiModelProperty(value = "Contact Name/Label", dataType = "String")
	private String name;

	@ApiModelProperty(value = "Contact Notes", dataType = "String")
	private String notes;

	@ApiModelProperty(value = "Contact Primmary Phone", dataType = "String")
	private String phone;

	@ApiModelProperty(value = "State", dataType = "String")
	private String state;

	@ApiModelProperty(value = "Street Name", dataType = "String")
	private String street;

	@ApiModelProperty(value = "Zip Code", dataType = "String")
	private int zip;
}
