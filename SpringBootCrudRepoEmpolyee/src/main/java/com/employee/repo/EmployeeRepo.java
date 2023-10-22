package com.employee.repo;

import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Id>{

}
