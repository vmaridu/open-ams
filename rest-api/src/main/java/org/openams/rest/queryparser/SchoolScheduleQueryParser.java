package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.SchoolSchedule;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class SchoolScheduleQueryParser extends AbstractQueryParser {

	private static final Path<SchoolSchedule> entityPath = Expressions.path(SchoolSchedule.class, "schoolSchedule");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("eventName", STRING_ADAPTER)
		    .put("status", BYTE_ADAPTER)
		    
		    .put("startDtt", DATE_TIME_ADAPTER)
		    .put("endDtt", DATE_TIME_ADAPTER)
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .put("staff.id", STRING_ADAPTER)
		    
		    .build();
	

	@Override
	protected Path<SchoolSchedule> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
