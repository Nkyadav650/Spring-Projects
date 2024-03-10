package com.eidiko.employee.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessLevel {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long empId;
	private long accessLevelId;
	}
