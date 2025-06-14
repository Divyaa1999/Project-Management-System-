package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	
	    Optional<Project> findById(String projectId);

	    List<Project> findByClient_ClientId(String clientId);  // get projects by client
	}


