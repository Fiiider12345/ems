package fiiider.fullstack.employeemanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiiider.fullstack.employeemanagementsystem.exception.ResourceNotFoundException;
import fiiider.fullstack.employeemanagementsystem.model.Employee;
import fiiider.fullstack.employeemanagementsystem.repository.EmployeeRepository;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping(path = "/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	// create an employee
	@PostMapping(path = "/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// update an employee
	@PutMapping(path = "/employees/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee oldEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + id));
		
		oldEmployee.setEmail(employee.getEmail());
		oldEmployee.setFirstName(employee.getFirstName());
		oldEmployee.setLastName(employee.getLastName());
		
		return employeeRepository.save(oldEmployee);
	}
	
	@DeleteMapping(path = "/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		Employee oldEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + id));
		
		employeeRepository.delete(oldEmployee);
	}
	
	@GetMapping(path = "/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id) {
		return employeeRepository.findEmployeeById(id);
	}
}
