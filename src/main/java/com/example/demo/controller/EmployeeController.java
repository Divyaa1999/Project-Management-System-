package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.*;

import jakarta.servlet.http.HttpSession;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Project;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("admin/employee/add")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.addEmployee(employee));
	}

	@GetMapping("admin/employee/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		System.out.println("hey here the employees");
		
		List<Employee> employees= employeeService.findByDesc();
		
		
		return ResponseEntity.ok(employees);
	}

	@GetMapping("admin/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee emp = employeeService.getEmployeeById(id);
		return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
	}

	@GetMapping("admin/employee/email/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
		Employee emp = employeeService.getEmployeeByEmail(email);
		return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
	}

	@GetMapping("admin/employee/date-range")
	public ResponseEntity<List<Employee>> getEmployeesByDateRange(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
		return ResponseEntity.ok(employeeService.getEmployeesByDateRange(start, end));
	}

	@DeleteMapping("admin/employee/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
		return ResponseEntity.ok(employeeService.deleteEmployee(id));
	}

	@PutMapping("admin/employee/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.updateEmployee(employee));
	}

	@GetMapping("admin/employee/{id}/project")
	public ResponseEntity<Project> getProjectByEmployeeId(@PathVariable String id) {
		Project project = employeeService.getProjectByEmployeeId(id);
		return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
	}

	@GetMapping("admin/employee/bench")
	public ResponseEntity<List<Employee>> getBenchEmployees() {
		return ResponseEntity.ok(employeeService.getBenchEmployees());
	}

	@PostMapping("admin/employee/{empId}/assign/{projectId}")
	public ResponseEntity<String> assignProject(@PathVariable String empId, @PathVariable String projectId) {
		return ResponseEntity.ok(employeeService.assignProject(empId, projectId));
	}

	@PutMapping("admin/employee/{empId}/release")
	public ResponseEntity<String> releaseProject(@PathVariable String empId) {
		return ResponseEntity.ok(employeeService.releaseProject(empId));
	}
	@GetMapping("employee/info/{empId}")
    public ResponseEntity<Employee> getEmployeeWithProject(@PathVariable String empId) {
        Employee emp = employeeService.getEmployeeWithProject(empId);
        return ResponseEntity.ok(emp);
	
		
	}
	@GetMapping("/check-session")
	public String checkSession(HttpSession session) {
	    return "Session ID: " + session.getId();
	}

	

}
