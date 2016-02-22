package org.openams.rest.jpa.repository;

import java.util.List;

import org.openams.rest.jpa.entity.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule,Integer>{
	
	List<SchoolSchedule> findByEventName(String eventName);
	
}
