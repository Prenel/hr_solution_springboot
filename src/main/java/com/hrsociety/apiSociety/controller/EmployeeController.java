package com.hrsociety.apiSociety.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hrsociety.apiSociety.model.Employee;

import com.hrsociety.apiSociety.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/api/employees")
	public Iterable<Employee> getEmployees()
	{
		Iterable<Employee> employees = employeeService.getEmployees();
		
		return employees;
	}
	
	@PostMapping("/api/employees")
	public Employee postEmployee(@RequestBody Employee employee)
	{
		Employee newEmployee = null;
		if (employee != null) {
			newEmployee = employeeService.saveEmployee(employee);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee must be not null");
		}
		
		return newEmployee;
	}
	
	@PutMapping("/api/employees/{id}")
	public Employee putEmployee(@PathVariable Long id, @RequestBody Employee employee)
	{
		Optional<Employee> oldEmployee = employeeService.getEmployee(id);
		Employee newEmployee = null;
		
		if (oldEmployee.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		} else {
			newEmployee = employeeService.updateEmployee(employee, oldEmployee.get());
		}
		
		return newEmployee;
	}
	
	@DeleteMapping("/api/employees/{id}")
	public ResponseStatusException deleteEmployee(@PathVariable Long id)
	{
		Optional<Employee> employee = employeeService.getEmployee(id);
		
		if (employee.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		} else {
			employeeService.deleteEmployee(employee.get().getId());
		}
		
		return new ResponseStatusException(HttpStatus.OK, "Employee has been deleted");
	}
}


