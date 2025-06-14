package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Project;
@Service
public interface EmployeeService {
	    String addEmployee(Employee employee);
	    List<Employee> findByDesc();

	    Employee getEmployeeById(String id);
	    Employee getEmployeeByEmail(String email);
	    List<Employee> getEmployeesByDateRange(Date start, Date end);
	    String deleteEmployee(String id);
	    String updateEmployee(Employee employee);
	    Project getProjectByEmployeeId(String empId);
	    List<Employee> getBenchEmployees();
	    String assignProject(String empId, String projectId);
	    String releaseProject(String empId);
	    Employee getEmployeeWithProject(String empId);
	}


