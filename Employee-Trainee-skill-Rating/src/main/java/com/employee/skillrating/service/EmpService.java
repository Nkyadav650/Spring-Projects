package com.employee.skillrating.service;


import java.util.List;

import com.employee.skillrating.entity.Employee;

public interface EmpService {

	public void sendEmailWithAttachment( Employee emailRequest,Long empId);
	public List<Employee> getAll();
}
