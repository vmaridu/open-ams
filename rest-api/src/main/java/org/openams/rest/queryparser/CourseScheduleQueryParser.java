package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.entity.enums.CourseScheduleStatus;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class CourseScheduleQueryParser extends AbstractQueryParser {

	private static final Path<CourseSchedule> entityPath = Expressions.path(CourseSchedule.class, "courseSchedule");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("name", STRING_ADAPTER)
		    .put("desc", STRING_ADAPTER)
		    
		    .put("location", STRING_ADAPTER)
		    .put("term", STRING_ADAPTER)
		    
		    .put("course.id", STRING_ADAPTER)
		    .put("staff.id", STRING_ADAPTER)
		    
		    .put("startDt", DATE_ADAPTER)
		    .put("endDt", DATE_ADAPTER)
		    .put("startT", TIME_ADAPTER)
		    .put("endT", TIME_ADAPTER)
		    
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .put("status",  new ValueObjectAdapter(CourseScheduleStatus.class, CourseScheduleStatus :: valueOf))
		    
		    
		    .build();
	

	@Override
	protected Path<CourseSchedule> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
