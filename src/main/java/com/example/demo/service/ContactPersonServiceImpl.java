package com.example.demo.service;

import com.example.demo.Model.Client;
import com.example.demo.Model.ContactPerson;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

	@Autowired
	private ContactPersonRepository contactPersonRepository;

	@Override
	public List<ContactPerson> getAllByClientId(String clientId) {
		return contactPersonRepository.findByClientId(clientId);
	}

}
