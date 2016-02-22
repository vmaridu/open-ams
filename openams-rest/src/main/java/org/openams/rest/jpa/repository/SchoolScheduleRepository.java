package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule,Integer>{
	
	Collection<SchoolSchedule> findByEventName(String eventName);
	
}
