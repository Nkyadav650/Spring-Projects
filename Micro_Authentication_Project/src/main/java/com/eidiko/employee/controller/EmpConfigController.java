package com.eidiko.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.eidiko.employee.exception.ResourceNotFoundException;
import com.eidiko.employee.model.Employee;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/empConfig")
@Slf4j
public class EmpConfigController {

	@Autowired
	private RestTemplate restTemplate;
	private final String url = "http://localhost:2001/empConfig";
	Map<String, Object> response = new HashMap<>();

	@GetMapping("/getAllEmployeeUnderManager/{managerId}") //Ok
	public ResponseEntity<Map<String, Object>> getAllEmployeeUnderManager( @PathVariable Long managerId){
		List<Employee> manager = restTemplate.exchange( url + "/getAllEmployeeUnderManager/" + managerId,
			    HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {}).getBody();
		if(manager!=null) {
		log.info("in side Authentication project EmpConfigController:"+manager.toString());
		response.put("Data", manager);
		response.put("status", "true");
		response.put("Result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK); 
		}else {
			throw new ResourceNotFoundException("Data not found !!");
		}
	}
}
