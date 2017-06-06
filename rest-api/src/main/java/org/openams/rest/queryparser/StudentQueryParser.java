package org.openams.rest.queryparser;

import java.util.Map;

import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.entity.enums.Gender;
import org.openams.rest.jpa.entity.enums.UserStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class StudentQueryParser extends AbstractQueryParser {

	private static final Path<Student> entityPath = Expressions.path(Student.class, "student");
	
	private static final Map<String, ValueObjectAdapter> keyToAdapterMap = ImmutableMap.<String, ValueObjectAdapter>builder()
		    .put("level", AbstractQueryParser.STRING_ADAPTER)
		    .put("parentEmail", AbstractQueryParser.STRING_ADAPTER)
		    .put("rollNumber", AbstractQueryParser.STRING_ADAPTER)
		    .put("id", AbstractQueryParser.STRING_ADAPTER)
		    .put("prefix", AbstractQueryParser.STRING_ADAPTER)
		    .put("fName", AbstractQueryParser.STRING_ADAPTER)
		    .put("mName", AbstractQueryParser.STRING_ADAPTER)
		    .put("lName", AbstractQueryParser.STRING_ADAPTER)
		    .put("suffix", AbstractQueryParser.STRING_ADAPTER)
		    
		    .put("user.userName", AbstractQueryParser.STRING_ADAPTER)
		    
		    .put("ssn", AbstractQueryParser.INTEGER_ADAPTER)
		    
		    .put("height", AbstractQueryParser.FLOAT_ADAPTER)
		    
		    .put("dob", AbstractQueryParser.EPOCH_DATE_ADAPTER)
		    .put("joiningDtt", AbstractQueryParser.EPOCH_DATE_ADAPTER)
		    .put("modifiedDtt", AbstractQueryParser.EPOCH_DATE_ADAPTER)
		    
		    .put("gender",  new ValueObjectAdapter(Gender.class, Gender :: valueOf))
		    .put("user.status", new ValueObjectAdapter(UserStatus.class, UserStatus :: valueOf))

		    .build();
	

	@Override
	protected Path<Student> getEntityPath() {
		return entityPath;
	}

	@Override
	public Map<String, ValueObjectAdapter> getKeyToAdapterMap() {
		return keyToAdapterMap;
	}

}
