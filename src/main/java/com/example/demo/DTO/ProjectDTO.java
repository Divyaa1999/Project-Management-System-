package com.example.demo.DTO;

import java.util.List;

public class ProjectDTO {
    private String projectId;
    private String projectName;
    private List<EmployeeDTO> employees;

    public ProjectDTO(String projectId, String projectName, List<EmployeeDTO> employees) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.employees = employees;
    }

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

    // Getters
}
