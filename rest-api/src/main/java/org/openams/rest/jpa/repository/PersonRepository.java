package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends BaseRepository<Person,String> {

	@Query("SELECT p FROM Person p WHERE p.user.userName = :userName")
	public Collection<Person> findByUserName(@Param("userName") String userName);
}
