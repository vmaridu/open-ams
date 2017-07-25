package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.Test;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class TestQueryParser extends AbstractQueryParser {

	private static final Path<Test> entityPath = Expressions.path(Test.class, "test");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("name", STRING_ADAPTER)
		    
		    .put("maxGrade", STRING_ADAPTER)
		    .put("maxScore", FLOAT_ADAPTER)
		    
		    .put("testType", STRING_ADAPTER)
		    .put("refId", STRING_ADAPTER)
		    
		    .put("startDtt", DATE_TIME_ADAPTER)
		    .put("endDtt", DATE_TIME_ADAPTER)
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .build();
	

	@Override
	protected Path<Test> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
