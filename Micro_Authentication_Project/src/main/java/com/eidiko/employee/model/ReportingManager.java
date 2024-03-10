package com.eidiko.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportingManager {

	private long id;
	private long empId;
	private long managerId;
	
}
