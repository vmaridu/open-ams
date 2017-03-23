package org.openams.rest.jpa.repository;

import org.openams.rest.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>{

	//List<User> findByEMail(String eMail);
}
