package org.openams.rest.queryparser;

import java.util.Collection;

import org.openams.rest.jpa.entity.Student;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class StudentQueryParser extends AbstractQueryParser{

	Path<Student> entityPath = Expressions.path(Student.class, "student");
	
	@Override
	public Predicate toLeafPredicate(String key, Ops operator, Collection<String> values) {
		Path<String> propPath = Expressions.path(String.class, entityPath , key);
		return Expressions.predicate(operator, propPath, Expressions.constant(values));
	}

	@Override
	public Predicate toLeafPredicate(String key, Ops operator, String value) {
		Path<String> propPath = Expressions.path(String.class, entityPath , key);
		return Expressions.predicate(operator, propPath, Expressions.constant(value));
	}

}
