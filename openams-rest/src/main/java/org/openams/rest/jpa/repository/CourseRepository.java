package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer>{
	
	Collection<Course> findByName(String name);
}
