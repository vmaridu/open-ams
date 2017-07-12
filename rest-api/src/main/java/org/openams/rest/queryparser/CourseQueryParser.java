package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.Course;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class CourseQueryParser extends AbstractQueryParser {

	private static final Path<Course> entityPath = Expressions.path(Course.class, "course");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("credits", BYTE_ADAPTER)
		    .put("dept", STRING_ADAPTER)
		    .put("name", STRING_ADAPTER)
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .build();
	

	@Override
	protected Path<Course> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
