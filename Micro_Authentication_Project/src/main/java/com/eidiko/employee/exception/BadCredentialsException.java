package com.eidiko.employee.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadCredentialsException(String message) {
		 super(message);
	}
}
