package org.openams.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Fee Payment Details")
public class PaymentModel {

	@ApiModelProperty(value = "ID", dataType = "String")
	private String id;

	@ApiModelProperty(value = "Name or Label", dataType = "String")
	private String name;
	
	@ApiModelProperty(value = "Payment Amount", dataType = "Float")
	private float amount;

	@ApiModelProperty(value = "Comment", dataType = "String")
	private String comment;

	@ApiModelProperty(value = "Fee ID", dataType = "String")
	private String feeId;
	
	@ApiModelProperty(value = "Modified DateTime", dataType = "DateTime")
	@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
	private Date modifiedDtt;
	
}
