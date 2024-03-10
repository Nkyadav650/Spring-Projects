package com.eidiko.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.eidiko.employee.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
	

//	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private JwtService jwtService;
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	public void validateToken(String token ,UserDetails user) {
		try {
			System.out.println("authService: "+token);
		jwtService.validateToken(token,user);
		log.info("token validated "+token);
		}catch(Exception ex) {
			throw new ResourceNotFoundException("Resource Not Found !!");
		}
	}

	
}
