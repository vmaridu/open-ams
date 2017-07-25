package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.Fee;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class FeeQueryParser extends AbstractQueryParser {

	private static final Path<Fee> entityPath = Expressions.path(Fee.class, "fee");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("name", STRING_ADAPTER)
		    .put("academicTerm", STRING_ADAPTER)
		    
		    .put("amount", FLOAT_ADAPTER)
		    
		    .put("student.id", STRING_ADAPTER)
		    
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .build();
	

	@Override
	protected Path<Fee> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
