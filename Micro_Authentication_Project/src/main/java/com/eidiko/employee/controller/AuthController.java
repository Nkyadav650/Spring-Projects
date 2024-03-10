package com.eidiko.employee.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eidiko.employee.exception.TokenException;
import com.eidiko.employee.filter.JwtAuthFilter;
import com.eidiko.employee.model.CustomUserDetails;
import com.eidiko.employee.model.Employee;
import com.eidiko.employee.model.JwtRequest;
import com.eidiko.employee.service.AuthService;
import com.eidiko.employee.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	 @Autowired private RestTemplate restTemplate;
	@Autowired
	private CustomUserDetailsService userService;
	@Autowired
	private AuthService authService;

	Map<String, Object> response = new HashMap<>();

	@PostMapping("/token")
	public ResponseEntity<Map<String, Object>> getToken(@RequestBody JwtRequest request) {
		System.out.println(request.getEmail() + "," + request.getPassword());
		Authentication authenticate = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println("Authentication :" + authenticate);
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(request.getEmail());

		if (authenticate.isAuthenticated()) {
			log.info("token Generated !!");
			response.put("Token", authService.generateToken(request.getEmail()));
			response.put("AccessId", user.getAccessId());
			response.put("status", HttpStatus.OK);
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			log.error("Error : ", BadCredentialsException.class);
			throw new BadCredentialsException("Providing data not Vaild");
		}
	}

	@GetMapping("/validate/{token}")
	public String validToken(@PathVariable String token, @RequestBody UserDetails user) {
		try {
			System.out.println(token);
			authService.validateToken(token, user);
			log.info("token generated : " + token);
			return "Message: Token is valid !!";
		} catch (TokenException te) {
			log.error("Error : ", TokenException.class);
			throw new TokenException("Token Not Generated !!");

		}
	}

	@GetMapping("/getFor111")
	public String getFor111() {
		return "This is for only Admin !! ";
	}

	@GetMapping("/getFor333")
	public String getFor333() {
		return "This is for only Manager !!";
	}

	@GetMapping("/getFor444")
	public String getFor444() {
		return "This is for only developer !!";
	}

	@GetMapping("/getFor555")
	public String getFor555() {
		return "This is for only Employee !!";
	}

	
	private final String url = "http://localhost:2001/employee";
	@GetMapping("/getEmp/{empId}")
	public Employee getEmployee(@PathVariable Long empId) {
		System.out.println("EmpController.getEmployee()"+empId);
		Employee employee = (Employee) restTemplate.getForObject(url + "/getEmp/" + empId, Object.class);
		return employee;
	}

	@GetMapping("/getAll")
	public List<Employee> getAll() {
		System.out.println("AuthController.getAll()"+"Request getAll coming");
		return null;
	}

}
