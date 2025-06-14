package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Project;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public String addEmployee(Employee employee) {
		if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
			return "Email already in use";
		}

		int count = (int) employeeRepository.count() + 1;
		String newId = String.format("JTC-%03d", count);
		employee.setId(newId);
		employee.setDoj(new Date());
		employeeRepository.save(employee);
		return "Employee registered with ID: " + newId;
	}

	public List<Employee> findByDesc() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "doj"));

	}

	@Override
	public Employee getEmployeeById(String id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email).orElse(null);
	}

	@Override
	public List<Employee> getEmployeesByDateRange(Date start, Date end) {
		return employeeRepository.findAllByDojBetween(start, end);
	}

	@Override
	public String deleteEmployee(String id) {
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return "Employee deleted";
		}
		return "Employee not found";
	}

	@Override
	public String updateEmployee(Employee updatedEmp) {
		Employee existing = employeeRepository.findById(updatedEmp.getId()).orElse(null);
		if (existing == null)
			return "Employee not found";

		// Don't update ID or Email
		existing.setName(updatedEmp.getName());
		existing.setDept(updatedEmp.getDept());
		existing.setPhone(updatedEmp.getPhone());
		employeeRepository.save(existing);
		return "Employee updated successfully";
	}

	@Override
	public Project getProjectByEmployeeId(String empId) {
		Employee emp = employeeRepository.findById(empId).orElse(null);
		return emp != null ? emp.getProject() : null;
	}

	@Override
	public List<Employee> getBenchEmployees() {
		return employeeRepository.findAll().stream().filter(e -> e.getProject() == null).collect(Collectors.toList());
	}

	@Override
	public String assignProject(String empId, String projectId) {
		Optional<Employee> empOpt = employeeRepository.findById(empId);
		Optional<Project> projOpt = projectRepository.findById(projectId);

		if (empOpt.isEmpty())
			return "Employee not found";
		if (projOpt.isEmpty())
			return "Project not found";

		Employee emp = empOpt.get();
		if (emp.getProject() != null) {
			return "Employee is already assigned to a project. Release it first.";
		}

		emp.setProject(projOpt.get());
		employeeRepository.save(emp);
		return "Project assigned successfully to employee " + empId;
	}

	@Override
	public String releaseProject(String empId) {
		Optional<Employee> empOpt = employeeRepository.findById(empId);

		if (empOpt.isEmpty())
			return "Employee not found";

		Employee emp = empOpt.get();
		if (emp.getProject() == null) {
			return "Employee is already on the bench";
		}

		emp.setProject(null);
		employeeRepository.save(emp);
		return "Employee released from the project.";
	}
    @Transactional
	public Employee getEmployeeWithProject(String empId) {

		Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));

		return emp;
	}

}
