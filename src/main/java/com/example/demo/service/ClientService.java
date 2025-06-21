package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.Model.Client;
@Service
public interface ClientService {
	Client saveClient(Client client);
    List<Client> getAllClients();
    Client getClientById(String id);
    void deleteClient(String id);
    Client updateClient(String id, Client updatedClient);
    ClientDTO getClientDetails(String clientId);
}
