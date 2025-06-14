package com.example.demo.service;



//package: com.example.project.service.impl

import com.example.demo.service.*;
import com.example.demo.Model.Client;
import com.example.demo.Repository.*;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
 

	// TODO Auto-generated method stub
	
}
}

