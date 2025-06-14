package com.example.demo.service;

import org.springframework.stereotype.Service;

	// package: com.example.project.service

	import com.example.demo.Model.*;
	import java.util.List;
   @Service
	public interface ContactPersonService {
	    List<ContactPerson> getAllByClientId(String clientId);
	}


