package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	// Find by email (for unique email validation and lookup)
	Optional<Employee> findByEmail(String email);

	// Find employees joined within a date range
	List<Employee> findAllByDojBetween(Date startDate, Date endDate);
	
	@Query("SELECT e FROM Employee e JOIN FETCH e.project WHERE e.id = :empId")
	Optional<Employee> findById(@Param("empId") String empId);
}
