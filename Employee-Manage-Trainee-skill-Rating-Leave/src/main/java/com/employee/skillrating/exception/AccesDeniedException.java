package com.employee.skillrating.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccesDeniedException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public AccesDeniedException(String message) {
		super(message);
		
	}

}