package org.openams.rest.queryparser;

import java.util.function.Function;

public class ValueObjectAdapter {

	private Class objectType;
	private Function<String,Object> toObjectFunction;
	
	public ValueObjectAdapter(Class objectType, Function<String,Object> toObjectFunction){
		this.objectType = objectType;
		this.toObjectFunction = toObjectFunction;
	}

	public Class getObjectType() {
		return objectType;
	}

	public Object getObject(String input) {
		try{
			return toObjectFunction.apply(input);
		}catch (Exception e) {
			throw new QueryParserRuntimeException("Unable to convert Value to Object : " + e.getMessage());
		}
	}
	
}
