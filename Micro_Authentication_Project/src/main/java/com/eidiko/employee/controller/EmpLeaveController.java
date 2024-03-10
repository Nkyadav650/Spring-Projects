package com.eidiko.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.eidiko.employee.model.AccessLevel;
import com.eidiko.employee.model.AccessRole;
import com.eidiko.employee.model.EmpLeave;
import com.eidiko.employee.model.LeaveType;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/empLeave")
@Slf4j
public class EmpLeaveController {

	@Autowired
	private RestTemplate restTemplate;
	private final String url = "http://localhost:2002/empLeave";
	
	Map<String, Object>response=new HashMap<>(); 
	
	//Save Entities 		-------------------------------------------------------------
	@PostMapping("/saveAccesslevel")
	public ResponseEntity<Map<String, Object>> saveAccessLevel(@RequestBody AccessLevel accessLevel){
		AccessLevel level=accessLevel;
		log.info("inside Authentication Project EmpLeaveController saveAccesslevel : "+accessLevel);
		try {
		//restTemplate.postForLocation(url+"/saveAccesslevel", level,AccessLevel.class);
		log.info("inside Authentication Project EmpLeaveController saveAccesslevel Data sent there: "+accessLevel);
		response.put("Message", "AccessLevel data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}catch(BadCredentialsException ex) {
		throw new BadCredentialsException("Credential Invalid !!");
	}
	}
	@PostMapping("/saveAccessRole")
	public ResponseEntity<Map<String, Object>> saveAccessRole(@RequestBody AccessRole accessRole){
		AccessRole role=accessRole;
		try {
		restTemplate.postForLocation(url+"/saveAccesslevel", role,AccessRole.class);
		response.put("Message", "AccessRole data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}catch(BadCredentialsException ex) {
		throw new BadCredentialsException("Credential Invalid !!");
	}
	}
	
	@PostMapping("/saveLeaveType")
	public ResponseEntity<Map<String, Object>> saveLeaveType(@RequestBody LeaveType leaveType){
		LeaveType leave=leaveType;
		try {
		restTemplate.postForLocation(url+"/saveAccesslevel", leave,LeaveType.class);		
		response.put("Message", "AccessLevel data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}catch(BadCredentialsException ex) {
			throw new BadCredentialsException("Credential Invalid !!");
		}
		}
	
	//get Data by Id		-------------------------------------------------------------
	@GetMapping("/getAccessLevel/{empId}")
	public ResponseEntity<Map<String, Object>> getAccessLevel(@PathVariable Long empId){
		AccessLevel accessLevel=restTemplate.getForObject(url+"/getAccessLevel/"+empId, AccessLevel.class);
		if(accessLevel!=null) {
		response.put("Data",accessLevel);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	@GetMapping("/getAccessRole/{accessId}")
	public ResponseEntity<Map<String, Object>> getAccessRole(@PathVariable Long accessId){
		AccessRole accessRole=restTemplate.getForObject(url+"/getAccessRole/"+accessId, AccessRole.class);
		if(accessRole!=null) {
		response.put("Data",accessRole);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	@GetMapping("/getEmpLeaveByEmpId/{empId}")
	public ResponseEntity<Map<String, Object>> getEmpLeave(@PathVariable Long empId){
		List<EmpLeave> empLeave=restTemplate.getForObject(url+"/getEmpLeaveByEmpId/"+empId, List.class);
		if(empLeave!=null) {
		response.put("Data",empLeave);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	   
	
	
	//Get All Entity Data		-------------------------------------------------------------
	@GetMapping("/getAllAccessLevel")
	public List<AccessLevel> getAllAccessLevel(){
		List<AccessLevel> accessLevel=restTemplate.getForObject(url+"/getAllAccessLevel", List.class);
		if(accessLevel!=null) {
			/*
			 * response.put("Data",accessLevel); response.put("status", HttpStatus.CREATED);
			 * response.put("result", "Success"); return new
			 * ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
			 */
			return accessLevel;
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllAccessRole")
	public ResponseEntity<Map<String, Object>> getAllAccessRole(){
		List<AccessRole> accessRole=restTemplate.getForObject(url+"/getAllAccessRole", List.class);
		if(accessRole!=null) {
		response.put("Data",accessRole);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllEmpLeave")
	public ResponseEntity<Map<String, Object>> getAllEmpLeave(){
		List<EmpLeave> empLeave=restTemplate.getForObject(url+"/getAllEmpLeave", List.class);
		if(empLeave!=null) {
		response.put("Data",empLeave);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllLeaveType")
	public ResponseEntity<Map<String, Object>> getAllLeaveType(){
		List<LeaveType> leaveType=restTemplate.getForObject(url+"/getAllEmpLeave", List.class);
		if(leaveType!=null) {
		response.put("Data",leaveType);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
}
