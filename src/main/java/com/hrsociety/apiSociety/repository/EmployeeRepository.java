package com.hrsociety.apiSociety.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hrsociety.apiSociety.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
