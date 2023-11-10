package com.employee.skillrating.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.skillrating.model.ApiError;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Id not available !!");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Access Denied !!");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	
	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<Object> handleBadCredentialException(BadCredentialException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Credential is invalid check  your credential  !!");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Resources not available !!");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

}