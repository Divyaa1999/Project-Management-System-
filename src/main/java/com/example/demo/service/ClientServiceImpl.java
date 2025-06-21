package com.example.demo.service;



//package: com.example.project.service.impl

import com.example.demo.service.*;
import com.example.demo.DTO.ClientDTO;
import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.ProjectDTO;
import com.example.demo.Model.Client;
import com.example.demo.Repository.*;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

 @Autowired
 private ClientRepository clientRepository;
 
 //@Autowired
// private ContactPersonRepository contactPersonRepository;

 @Override
 public Client saveClient(Client client) {
     // Set client reference in each contact person
     if (client.getContactPersons() != null) {
         client.getContactPersons().forEach(cp -> cp.setClient(client));
     }
     int count = (int) clientRepository.count() + 1;
		String newId = String.format("Client-%03d", count);
		client.setId(newId);
     return clientRepository.save(client);
 }

 @Override
 public List<Client> getAllClients() {
     return clientRepository.findAll();
 }

 @Override
 public Client getClientById(String id) {
     return clientRepository.findById(id).orElse(null);
 }

 @Override
 public void deleteClient(String id) {
     clientRepository.deleteById(id);
 }

 @Override
 public Client updateClient(String id, Client updatedClient) {
     Client existing = clientRepository.findById(id).orElse(null);
     if (existing != null) {
         existing.setName(updatedClient.getName());
         existing.setRelationshipDate(updatedClient.getRelationshipDate());

         // Handle contactPersons
         existing.getContactPersons().clear();
         updatedClient.getContactPersons().forEach(cp -> cp.setClient(existing));
         existing.getContactPersons().addAll(updatedClient.getContactPersons());

         return clientRepository.save(existing);
     }
     return null;
 
 }
	// TODO Auto-generated method stub
     public ClientDTO getClientDetails(String clientId) {
         Client client = clientRepository.findById(clientId)
                 .orElseThrow(() -> new RuntimeException("Client not found"));

         List<ProjectDTO> projectDTOs = client.getProjects().stream().map(project -> {
             List<EmployeeDTO> employeeDTOs = project.getEmployees().stream()
                     .map(emp -> new EmployeeDTO(emp.getName()))
                     .collect(Collectors.toList());

             return new ProjectDTO(project.getId(), project.getName(), employeeDTOs);
         }).collect(Collectors.toList());

         

     return new ClientDTO(client.getId(), client.getName(), projectDTOs);
 }

}


