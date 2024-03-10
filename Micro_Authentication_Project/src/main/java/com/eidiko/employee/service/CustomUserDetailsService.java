package com.eidiko.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eidiko.employee.model.AccessLevel;
import com.eidiko.employee.model.CustomUserDetails;
import com.eidiko.employee.model.Employee;

import jakarta.ws.rs.ext.ParamConverter.Lazy;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Lazy
public class CustomUserDetailsService implements UserDetailsService {
	private final RestTemplate restTemplate;

	@Autowired
	public CustomUserDetailsService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// load user from user database
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LoadUserByUsarName : " + username);

		Employee emp = restTemplate.getForObject("http://localhost:2001/employee/getEmpByEmail/" + username,
				Employee.class);
		System.out.println("Employee in CustomUserdetailsService : " + emp);
		// read user from data base
		if (emp == null) {
			throw new UsernameNotFoundException("Employee does not exist !");
		}
		 Long id=emp.getEmpId();
		 String accessId="";
		List<AccessLevel> access = restTemplate.exchange("http://localhost:2002/empLeave/getAllAccessLevel",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<AccessLevel>>() {
				}).getBody();

		log.info("Inside the CustomUserDetailService in Authentication Project : " + access);
		for(AccessLevel level:access) {
			if(level.getEmpId()==id) {
				accessId=String.valueOf(level.getAccessLevelId());
			}
		}		

		CustomUserDetails user = new CustomUserDetails(emp.getEmail(), emp.getPassword(), accessId/*,authorities */);
		System.out.println("CustomeUserDetiails value in side CustomUserDetailService: " + user);
		return user;

	}
	

}
