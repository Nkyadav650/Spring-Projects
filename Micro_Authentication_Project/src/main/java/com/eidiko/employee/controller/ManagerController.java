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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.eidiko.employee.exception.BadCredentialsException;
import com.eidiko.employee.exception.ResourceNotFoundException;
import com.eidiko.employee.model.ManagerEntity;
import com.eidiko.employee.model.ReportingManager;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

	@Autowired
	RestTemplate restTemplate;

	Map<String, Object> response = new HashMap<>();

	private final String url = "http://localhost:2003/manager";

	@PostMapping("/saveManager")
	public ResponseEntity<Map<String, Object>> saveManagerEntity(@RequestBody ManagerEntity managerEntity) {
		ManagerEntity manager = managerEntity;
		try {
			restTemplate.postForObject(url + "/saveManager", manager, ManagerEntity.class);

			log.info("manager entity data getting from service !!");
			response.put("Message", "Manager data saveed !!");
			response.put("status", HttpStatus.CREATED);
			response.put("result", "Success");
			log.info("response sent:" + response);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (BadCredentialsException bc) {
			throw new BadCredentialsException(bc.getMessage());
		}
	}

	@PostMapping("/saveReportingManager")
	public ResponseEntity<Map<String, Object>> saveReportingManagerEntity(
			@RequestBody ReportingManager reportingManager) {
		ReportingManager manager = reportingManager;
		try {
			restTemplate.postForObject(url + "/saveReportingManager", manager, ManagerEntity.class);
			log.info("manager entity data getting from service !!");
			response.put("Message", "ReportingManager data saveed !!");
			response.put("status", HttpStatus.CREATED);
			response.put("result", "Success");
			log.info("response sent:" + response);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (BadCredentialsException bc) {
			throw new BadCredentialsException(bc.getMessage());
		}
	}

	@GetMapping("/getManagerByEmpId/{empId}")
	public ResponseEntity<Map<String, Object>> getManagerById(@PathVariable Long empId) {
		log.info("******* Manager Controller in Authentication : " + empId);
		ManagerEntity manager = restTemplate.getForObject(url + "/getManagerByEmpId/" + empId, ManagerEntity.class);
		log.info("******* Manager Controller in Authentication : " + manager);
		if (manager != null) {
			log.info("managById is Not Null !!");
			response.put("Data", manager);
			response.put("status", HttpStatus.OK);
			response.put("result", "Success");
			log.info("response created: " + response);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}

	@GetMapping("/getManagerByManagerId/{managerId}")
	public ResponseEntity<Map<String, Object>> getManagerByManagerId(@PathVariable Long managerId) {
		ManagerEntity manager = restTemplate.getForObject(url + "/getManagerByManagerId/" + managerId,
				ManagerEntity.class);
		if (manager != null) {
			log.info("managById is Not Null !!");
			response.put("Data", manager);
			response.put("status", HttpStatus.OK);
			response.put("result", "Success");
			log.info("response created: " + response);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {

			throw new ResourceNotFoundException("Data is Null");
		}
	}

	@GetMapping("/getAllManager")
	public ResponseEntity<Map<String, Object>> getAllManager() {
		List<ManagerEntity> manager = restTemplate.exchange(url + "/getAllManager", 
				 HttpMethod.GET,null,new ParameterizedTypeReference<List<ManagerEntity>>() {}).getBody();
		if (manager != null) {
			response.put("Message", "Data Fetched Successfully");
			response.put("Data", manager);
			response.put("Total Manager", manager.size());
			response.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Resource Not Available");
		}
	}

	@GetMapping("/getAllEmployeeByReportingManager/{managerId}")
	public ResponseEntity<Map<String, Object>> getAllEmployeeByReportingManager(@PathVariable Long managerId) {
		List<ReportingManager> manager = restTemplate.exchange(url + "/getAllEmployeeByReportingManager/" + managerId,
			    HttpMethod.GET,null,new ParameterizedTypeReference<List<ReportingManager>>() {}).getBody();
		if (manager != null) {
			response.put("Message", "Data Fetched Successfully");
			response.put("Data", manager);
			response.put("Total Manager", manager.size());
			response.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Resource Not Available");
		}
	}

	@GetMapping("/getReportingManagerByempId/{empId}")
	public ResponseEntity<Map<String, Object>> getReportingManagerByempId(@PathVariable Long empId) {
		ManagerEntity manager = restTemplate.getForObject(url + "/getReportingManagerByempId/" + empId,
				ManagerEntity.class);
		if (manager != null) {
			response.put("Message", "Data Fetched Successfully");
			response.put("Data", manager);
			response.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Resource Not Available");
		}
	}

}
