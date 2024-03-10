package com.eidiko.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.eidiko.employee.enums.EnumStatus;
import com.eidiko.employee.exception.BadCredentialsException;
import com.eidiko.employee.exception.ResourceNotFoundException;
import com.eidiko.employee.model.EmpLeave;

@RestController
@RequestMapping("/leaveConfig")
public class LeaveConfiController {

	@Autowired
	private RestTemplate restTemplate;
	private final String url = "http://localhost:2002/employee";
	
	Map<String, Object>response=new HashMap<>();
	
	
	@PutMapping("/updatEmpLeaveByempId/{empId}")
	public ResponseEntity<Map<String,Object>> updatEmpLeaveByempId(@RequestBody EmpLeave empLeave,@PathVariable Long empId){
		EmpLeave leave=empLeave;
		try {
			restTemplate.postForObject(url+"/updatEmpLeaveByempId/"+empId, leave, EmpLeave.class);	
		response.put("Message", "EmpLeave data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}catch(BadCredentialsException ex) {
		throw new BadCredentialsException("Credential Invalid !!");
	}
	}
	
	@PutMapping("/updateEmpLeaveStatus/{empId}/{managerId}")
	public ResponseEntity<Map<String, Object>> updateEmpLeaveStatusBymanager(@RequestBody EmpLeave empLeave,
			@PathVariable Long empId, @PathVariable Long managerId) {
		EmpLeave leave=empLeave;
		try {
			restTemplate.postForObject(url+"/updateEmpLeaveStatus/"+empId+"/"+managerId, leave, EmpLeave.class);			
		response.put("Message", "EmpLeave data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch(BadCredentialsException ex) {
			throw new BadCredentialsException("Credential Invalid !!");
		}
	}

	@GetMapping("/getAllEmpLeaveByStatus/{status}")
	public ResponseEntity<Map<String, Object>> getAllEmpLeaveByStatus(@PathVariable EnumStatus status) {
		List<EmpLeave> empLeaveList =restTemplate.getForObject(url+"/getAllEmpLeaveByStatus/"+status, List.class);
		if(empLeaveList!=null) {
		response.put("Message", "EmpLeave data saveed !!");
		response.put("Data", empLeaveList);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Resouce Not Found !!");
		}
	}
	
	@GetMapping("/getAllEmpLeaveByStatusByEmpId/{empId}/{status}")
	public ResponseEntity<Map<String, Object>> getAllEmpLeaveByStatusByEmpId(@PathVariable Long empId,@PathVariable EnumStatus status) {
		List<EmpLeave> empLeaveList = restTemplate.getForObject(url+"/getAllEmpLeaveByStatusByEmpId/"+empId+"/"+status, List.class);
		if(empLeaveList!=null) {
		response.put("Message", "EmpLeave data saveed !!");
		response.put("Data", empLeaveList);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Resouce Not Found !!");
		}
	}
	
	
}
