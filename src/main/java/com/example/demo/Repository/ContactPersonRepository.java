package com.example.demo.Repository;


	// package: com.example.project.repository

	import com.example.demo.Model.*;
	import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
   @Repository
	public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {
	    List<ContactPerson> findByClientId(String clientId);
	}


