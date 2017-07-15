package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.StudentCourseEnrollment;
import org.openams.rest.jpa.entity.enums.StudentCourseEnrollmentStatus;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class StudentCourseEnrollmentQueryParser extends AbstractQueryParser {

	private static final Path<StudentCourseEnrollment> entityPath = Expressions.path(StudentCourseEnrollment.class, "studentCourseEnrollment");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
	
		    .put("id", STRING_ADAPTER)
		    .put("grade", STRING_ADAPTER)
		    
		    .put("courseSchedule.id", STRING_ADAPTER)
		    .put("student.id", STRING_ADAPTER)
		    
		    .put("enrolledDtt", DATE_TIME_ADAPTER)
		    .put("gradedDtt", DATE_TIME_ADAPTER)
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .put("status",  new ValueObjectAdapter(StudentCourseEnrollmentStatus.class, StudentCourseEnrollmentStatus :: valueOf))
		    
		    
		    .build();
	

	@Override
	protected Path<StudentCourseEnrollment> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
