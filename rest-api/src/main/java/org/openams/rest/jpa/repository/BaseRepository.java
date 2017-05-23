package org.openams.rest.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E,K extends Serializable> extends JpaRepository<E,K>  , QueryDslPredicateExecutor<E>{

}
