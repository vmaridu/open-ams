package org.openams.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.openams.rest.jpa.entity.enums.EyeColor;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.HairColor;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Person")
public class PersonModel {

	@ApiModelProperty(value = "Person ID", dataType = "String")
	private String id;
	
	@ApiModelProperty(value = "Person Name Prefix  Ex : Mr,Mrs", dataType = "String")
	private String prefix;
	
	@ApiModelProperty(value = "Person First Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String fName;
	
	@ApiModelProperty(value = "Person Middle Name", dataType = "String")
	private String mName;
	
	@ApiModelProperty(value = "Person Last Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String lName;
	
	@ApiModelProperty(value = "Person Name Suffux  Ex : Jr,Sr", dataType = "String")
	private String suffix;
	
	@ApiModelProperty(value = "Gender", dataType = "String")
	private Gender gender;
	
	@ApiModelProperty(value = "Eye Color", dataType = "String")
	private EyeColor eyeColor;
	
	@ApiModelProperty(value = "Hair Color", dataType = "String")
	private HairColor hairColor;
	
	@ApiModelProperty(value = "Race", dataType = "String")
	private String race;
	
	@ApiModelProperty(value = "Height", dataType = "Float")
	private Float height;
	
	@ApiModelProperty(value = "Weight", dataType = "Float")
	private Float weight;
	
	@ApiModelProperty(value = "Picture URL", dataType = "Float")
	private String pictureUri;
	
	@ApiModelProperty(value = "Date of Birth", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@ApiModelProperty(value = "Joining Date", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date joiningDtt;
	
	@ApiModelProperty(value = "Last Modified Date", dataType = "Date")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
	@ApiModelProperty(value = "SSN", dataType = "Integer")
	private Integer ssn;
	
	@ApiModelProperty(value = "User Account Details", dataType = "UserModel")
	private UserModel user;
	
	@ApiModelProperty(value = "Contact Details", dataType = "ContactModel")
	private ContactModel contact;
	
	@ApiModelProperty(value = "Emergency Contact Details", dataType = "ContactModel")
	private ContactModel emrContact;
	
}
