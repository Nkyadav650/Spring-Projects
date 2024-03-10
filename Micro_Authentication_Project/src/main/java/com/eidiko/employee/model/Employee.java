package com.eidiko.employee.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Employee {
	
	private long empId;
	private String empName;
	private Date doj;
	private long number;
	private String about;
	private String designation;
	private String email;
	private String password;
	

}