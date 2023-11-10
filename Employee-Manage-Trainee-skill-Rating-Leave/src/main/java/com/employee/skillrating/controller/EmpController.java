package com.employee.skillrating.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.skillrating.entity.Employee;
import com.employee.skillrating.exception.BadCredentialException;
import com.employee.skillrating.exception.IdNotFoundException;
import com.employee.skillrating.exception.ResourceNotFoundException;
import com.employee.skillrating.service.EmpService;

@RestController

public class EmpController {

	Logger logger= LoggerFactory.getLogger(EmpController.class);
    @Autowired
    private EmpService empService;
    
    Map<String,Object> response=new HashMap<>();
    
    @PostMapping("/save")
    public ResponseEntity<Map<String,Object>> saveEmp(@RequestBody Employee emp) throws BadCredentialException {
    	try {
    			empService.saveEmp(emp);
    			response.put("Message", "EMployee Data saved successfuly");
    			response.put("status", HttpStatus.CREATED);
    			response.put("Result", "Success");
    		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    	 }catch(Exception e) {
    	throw new BadCredentialException("Credential invalid !!");	 	
    	 }
    	 
    }
    
    @GetMapping("/getEmp/{empId}")
    public ResponseEntity<Map<String,Object>> getEmp(Long empId){
    	Employee employee=empService.getEmp(empId);
    	if(employee!=null) {response.put("Message", employee);
		 response.put("status", HttpStatus.CREATED);
		 response.put("Result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
   	}else {
   	throw new IdNotFoundException("Id not Available !!");
   	}
    }
    
    @GetMapping("/empAll")
    public ResponseEntity<Map<String,Object>> getAll() throws Exception{
    	List<Employee>employee=empService.getAll();
    	if(employee!=null) {response.put("Message", employee);
		 response.put("status", HttpStatus.CREATED);
		 response.put("Result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    	}else {
    	throw new ResourceNotFoundException("Resource not Available !!");
    	}
    }
    
 // @Scheduled(cron = "0 * 1-10 * * ?") // this is for every month 1th date to 10th date
    
	
}
