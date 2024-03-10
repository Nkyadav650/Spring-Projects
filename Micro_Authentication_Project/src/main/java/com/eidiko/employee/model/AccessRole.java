package com.eidiko.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccessRole {

	private long accessId;
	private String level;
	private String description;

}
