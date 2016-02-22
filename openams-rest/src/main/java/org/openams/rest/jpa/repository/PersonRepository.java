package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.repository.custom.PersonRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//TODO: User JpaSpecificationExecutor for Criteria Queries
public interface PersonRepository extends JpaRepository<Person,String>, PersonRepositoryCustom{

	@Query("SELECT p FROM Person p WHERE p.user.userName = :userName")
	public Collection<Person> findByUserName(String userName);
	
}
