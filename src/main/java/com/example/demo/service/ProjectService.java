package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.Project;

public interface ProjectService {

	Project addProject(Project project);

	List<Project> getAllProjects();

	Project getProjectById(String projectId);

	Project updateProject(String projectId, Project updatedProject);

	void deleteProject(String projectId);

	List<Project> getProjectsByClientId(String clientId);
}
