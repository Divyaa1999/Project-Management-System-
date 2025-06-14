package com.example.demo.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	@Id
	@Column(name="client_id")
	 private String clientId;
	
	 private String name;
	 
	 private Date relationshipDate;
	 
	 @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
	 private List<Project> projects;
	 
	 @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
	 private List<ContactPerson> contactPersons;

	public String getId() {
		return clientId;
	}

	public void setId(String id) {
		this.clientId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRelationshipDate() {
		return relationshipDate;
	}

	public void setRelationshipDate(Date relationshipDate) {
		this.relationshipDate = relationshipDate;
	}

	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(List<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}
	 
	 

}
