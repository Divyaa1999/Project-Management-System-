package com.example.demo.DTO;

import java.util.List;

public class ClientDTO {
    private String clientId;
    private String name;
    private List<ProjectDTO> projects;

    public ClientDTO(String clientId, String name, List<ProjectDTO> projects) {
        this.clientId = clientId;
        this.name = name;
        this.projects = projects;
    }

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}

    // Getters
}
