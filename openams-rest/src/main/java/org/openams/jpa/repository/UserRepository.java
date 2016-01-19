package org.openams.jpa.repository;

import org.openams.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String>{

	//List<User> findByLastName(String lastName);
	
}
