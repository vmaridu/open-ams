package org.openams.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Course Details")
public class CourseModel {

	@ApiModelProperty(value = "Course ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Name", dataType = "String")
	@NotNull
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "Number of Credits per Course", dataType = "byte")
	private Byte credits;
	
	@ApiModelProperty(value = "Department", dataType = "String")
	private String dept;
	
	@ApiModelProperty(value = "Description", dataType = "String")
	private String desc;
	
	@ApiModelProperty(value = "Modified Date", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
}
