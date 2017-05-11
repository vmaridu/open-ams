package org.openams.rest.jpa.repository;

import org.openams.rest.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UserRepository extends JpaRepository<User,String> , QueryDslPredicateExecutor<User>{

}
