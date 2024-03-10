package com.eidiko.employee.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eidiko.employee.model.ApiError;
@Component
@RestControllerAdvice
public class CustomExceptionHandler{

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> badCredentialsExceptionHandler(BadCredentialsException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Credentials Invalid !!");
		ApiError error=new ApiError(message,details,HttpStatus.BAD_GATEWAY,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNoTFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Resource Not Available in Db, Request Invalid !!");
		ApiError error=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Object> tokenExceptionHandler(TokenException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Credentials Invalid !!");
		ApiError error=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> idNotFoundExceptionHandler(IdNotFoundException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("ID Not Available in Db, Request Invalid !!");
		ApiError error=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
