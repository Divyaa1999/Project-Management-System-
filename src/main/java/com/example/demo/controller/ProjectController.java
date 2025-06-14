package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProjectController {

	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping("admin/project/add")
	public Project addProject(@RequestBody Project project) {
		return projectService.addProject(project);
	}

	@GetMapping("admin/project/all")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("admin/project/{projectId}")
	public Project getProjectById(@PathVariable String projectId) {
		return projectService.getProjectById(projectId);
	}

	@PutMapping("admin/project/update/{projectId}")
	public Project updateProject(@PathVariable String projectId, @RequestBody Project updatedProject) {
		return projectService.updateProject(projectId, updatedProject);
	}

	@DeleteMapping("admin/project/delete/{projectId}")
	public String deleteProject(@PathVariable String projectId) {
		projectService.deleteProject(projectId);
		return "Project with ID " + projectId + " deleted successfully.";
	}

	@GetMapping("admin/project/client/{clientId}")
	public List<Project> getProjectsByClientId(@PathVariable String clientId) {
		return projectService.getProjectsByClientId(clientId);
	}
}
