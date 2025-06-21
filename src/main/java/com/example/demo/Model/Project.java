package com.example.demo.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
     @Id
     @Column(name="project_id")
	 private String id;
     
     @Column(name="project_name")
     private String name;
     
     @Column(name="start_date")
     private LocalDate startDdate;
     
     @Column(name="end_date")
     private LocalDate endEnd;
     
     @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonBackReference
     private List<Employee> employees;
     
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "client_id")
     @JsonIgnore 
     private Client client;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDdate() {
		return startDdate;
	}

	public void setStartDdate(LocalDate startDdate) {
		this.startDdate = startDdate;
	}

	public LocalDate getEndEnd() {
		return endEnd;
	}

	public void setEndEnd(LocalDate endEnd) {
		this.endEnd = endEnd;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
     
     
     
}
