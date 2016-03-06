package org.openams.rest.jpa.repository;

import java.util.Collection;

import org.openams.rest.jpa.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff,String>{

	@Query("SELECT s FROM Staff s WHERE s.user.userName = :userName")
	public Collection<Staff> findByUserName(String userName);

}
