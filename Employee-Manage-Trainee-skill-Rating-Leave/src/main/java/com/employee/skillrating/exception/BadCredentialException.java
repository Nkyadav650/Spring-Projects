package com.employee.skillrating.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadCredentialException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BadCredentialException(String message) {
		super(message);
	}
}
