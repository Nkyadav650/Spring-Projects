package com.eidiko.employee.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEntity {

	private long id;
	private long empId;
	private long managerId;
	private String working;
	private String skill;
	private LocalDate startDate;
	private LocalDate endDate;
	private long modifiedBy;
	private long createdBy;
}
