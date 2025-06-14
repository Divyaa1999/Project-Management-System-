package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

//package: com.example.project.controller

import com.example.demo.Model.*;

import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ContactPersonService contactPersonService;

	@PostMapping("admin/client/addClient")
	public Client createClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}

	@GetMapping("admin/client/all")
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	@GetMapping("admin/client/{id}")
	public Client getClientById(@PathVariable String id) {
		return clientService.getClientById(id);
	}

	@GetMapping("admin/client/{id}/contacts")
	public List<ContactPerson> getContactPersonsByClientId(@PathVariable String id) {
		return contactPersonService.getAllByClientId(id);
	}

	@PutMapping("admin/client/{id}")
	public Client updateClient(@PathVariable String id, @RequestBody Client client) {
		return clientService.updateClient(id, client);
	}

	@DeleteMapping("admin/client/{id}")
	public void deleteClient(@PathVariable String id) {
		clientService.deleteClient(id);
	}
	@GetMapping("client/information")
	public String getClientInfo() {
		return "hey i am client";
	}
}
