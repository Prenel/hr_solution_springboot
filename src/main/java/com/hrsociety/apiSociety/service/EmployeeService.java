package com.hrsociety.apiSociety.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hrsociety.apiSociety.model.Employee;
import com.hrsociety.apiSociety.repository.EmployeeRepository;

import lombok.Data;

@Data
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Optional<Employee> getEmployee(final Long id) {
		return employeeRepository.findById(id);
	}
	
	public Iterable<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public void deleteEmployee(final Long id) {
		employeeRepository.deleteById(id);
	}
	
	public Employee saveEmployee(Employee employee) {
		
		if (employee.getFirstname() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname must be not null");
		}else if (employee.getLastname() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname must be not null");
		} else if (employee.getMail() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail must be not null");
		} else if (employee.getPassword() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be not null");
		}
		
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Employee newEmployee, Employee oldEmployee) {
		
		if (newEmployee.getFirstname() != null) {
			oldEmployee.setFirstname(newEmployee.getFirstname());
		}
		if (newEmployee.getLastname() != null) {
			oldEmployee.setLastname(newEmployee.getLastname());
		}
		if (newEmployee.getMail() != null) {
			oldEmployee.setMail(newEmployee.getMail());
		}
		if (newEmployee.getPassword() != null) {
			oldEmployee.setPassword(newEmployee.getPassword());
		}
		
		return employeeRepository.save(oldEmployee);
	}
}
