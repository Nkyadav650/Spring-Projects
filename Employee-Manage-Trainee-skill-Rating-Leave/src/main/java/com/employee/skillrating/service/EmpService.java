package com.employee.skillrating.service;

import java.util.List;

import com.employee.skillrating.entity.Employee;

public interface EmpService {

	// create
	public Employee saveEmp(Employee emp);

	// update
	public Employee updateEmp(Employee emp, Long empId);

	// delete
	public void deleteEmp(Long empId);

	// get EmpById
	public Employee getEmp(Long empId);

	// getAll
	public List<Employee> getAll() throws Exception;

	// for mail sending
	public void sendEmailWithAttachment() throws Exception;

}
