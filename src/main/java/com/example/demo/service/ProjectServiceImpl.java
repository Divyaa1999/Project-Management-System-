package com.example.demo.service;
import com.example.demo.service.*;


import com.example.demo.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Client;
import com.example.demo.Model.Project;
import com.example.demo.Repository.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
    	
        this.projectRepository = projectRepository;
    }

    @Override
    public Project addProject(Project project) {
    	int count = (int) projectRepository.count() + 1;
		String newId = String.format("PROJECT-%03d", count);
		project.setId(newId);
		
		String clientId = project.getClient().getId();
	    // ✅ Fetch existing Client from DB to ensure it's a managed entity
	    Client existingClient = clientRepository.findById(clientId)
	        .orElseThrow(() -> new RuntimeException("Client with ID " + clientId + " not found"));

	    // ✅ Set the managed client object into the project
	    project.setClient(existingClient);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(String projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    }

    @Override
    public Project updateProject(String projectId, Project updatedProject) {
        Project existing = getProjectById(projectId);
        existing.setName(updatedProject.getName());
        existing.setStartDdate(updatedProject.getStartDdate());

        // handle extended end-date logic
        existing.setEndEnd(updatedProject.getEndEnd());
        existing.setClient(updatedProject.getClient());

        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(String projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<Project> getProjectsByClientId(String clientId) {
        return projectRepository.findByClient_ClientId(clientId);
    }
}



