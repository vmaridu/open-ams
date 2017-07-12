package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.UserStatus;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Component
public class StaffQueryParser extends AbstractQueryParser {

	private static final Path<Staff> entityPath = Expressions.path(Staff.class, "staff");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
		   
			.put("altId", STRING_ADAPTER)
		    .put("designation", STRING_ADAPTER)
		    
		    .put("id", STRING_ADAPTER)
		    .put("prefix", STRING_ADAPTER)
		    .put("fName", STRING_ADAPTER)
		    .put("mName", STRING_ADAPTER)
		    .put("lName", STRING_ADAPTER)
		    .put("suffix", STRING_ADAPTER)
		    
		    .put("user.userName", STRING_ADAPTER)
		    
		    .put("ssn", INTEGER_ADAPTER)
		    
		    .put("height", FLOAT_ADAPTER)
		    
		    .put("dob", DATE_ADAPTER)
		    .put("joiningDtt", DATE_TIME_ADAPTER)
		    .put("modifiedDtt", DATE_TIME_ADAPTER)
		    
		    .put("gender",  new ValueObjectAdapter(Gender.class, Gender :: valueOf))
		    .put("user.status", new ValueObjectAdapter(UserStatus.class, UserStatus :: valueOf))

		    .build();
	

	@Override
	protected Path<Staff> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
