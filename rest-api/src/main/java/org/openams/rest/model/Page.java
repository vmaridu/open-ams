package org.openams.rest.model;

import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Paginated Wrapper")
public class Page<T> {
	
	@ApiModelProperty(value = "Page Index (0 based Index)", dataType = "int")
	private int page;

	@ApiModelProperty(value = "Requested number of Resources", dataType = "int")
	private int limit;
	
	@ApiModelProperty(value = "Total number of Elements", dataType = "long")
	private long total;
	
	@ApiModelProperty(value = "Number of Elements in Response", dataType = "int")
	private int size;
	
	@ApiModelProperty(value = "Total number of Pages", dataType = "int")
	private int totalPages;
	
	@ApiModelProperty(value = "Query Params for Previous Page; null if first Page", dataType = "String")
	private String prvious;
	
	@ApiModelProperty(value = "Query Params for Next Page; null if last page", dataType = "String")
	private String next;
	
	@ApiModelProperty(value = "Collection of Resources", dataType = "Collection<Type>")
	Collection<T> content;
}
