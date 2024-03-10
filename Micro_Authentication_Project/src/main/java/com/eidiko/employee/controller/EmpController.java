package com.eidiko.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.eidiko.employee.exception.BadCredentialsException;
import com.eidiko.employee.exception.IdNotFoundException;
import com.eidiko.employee.model.EmpLeave;
import com.eidiko.employee.model.Employee;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emp")
@Slf4j
public class EmpController {
	@Autowired
	private RestTemplate restTemplate;
	private final String url = "http://localhost:2001/employee";
	Map<String, Object> response = new HashMap<>();
	
	@PostMapping("/saveEmp")//ok
	public ResponseEntity<Map<String, Object>> saveEmp(@RequestBody Employee emp) {	
		try {
			Employee employee=emp;
			restTemplate.postForObject(url+"/saveEmp", employee, Employee.class);			
		response.put("Message", "EMployee Data saved successfuly");
		response.put("status", "true");
		response.put("Result", "Success");
		log.info(response.toString());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}catch(BadCredentialsException ex) {
		throw new BadCredentialsException("Credential Inalid !!");
	}
	}
	@GetMapping("/getEmp/{empId}")//Ok
	public Object getEmployee(@PathVariable Long empId) {
		System.out.println("EmpController.getEmployee()" + empId);
		Employee employee = restTemplate
				.exchange(url + "/getEmp/" + empId, HttpMethod.GET, null, new ParameterizedTypeReference<Employee>() {
				}).getBody();
		if (employee != null) {
			response.put("status", "true");
			response.put("Result", "Success");
			response.put("Data", employee);
			log.info(response.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			log.error(empId + " id is Not Available");
			throw new IdNotFoundException(empId + " id is Not Available");
		}
	}

	@DeleteMapping("/delete/{empId}")//Ok
	public ResponseEntity<Map<String, Object>> deleteEmp(@PathVariable long empId) {
		try {
		restTemplate.delete(url + "/delete/" + empId,void.class);		
		response.put("status", "true");
		response.put("Result", "Success");
		response.put("Message", "Deleted successFully");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}catch(IdNotFoundException ex) {
		throw new IdNotFoundException("Id not Available in Db !!");
	}
	}

	@GetMapping("/getAll")//ok
	public ResponseEntity<Map<String, Object>> getAll() {
		List<Employee> employee = restTemplate
				.exchange(url + "/getAll", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
		response.put("status", "true");
		response.put("Result", "Success");
		response.put("Data", employee);
		response.put("Total", employee.size());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/applyLeave/{empId}") //OK
	public ResponseEntity<Map<String, Object>> applyLeaveByEmp(@PathVariable Long empId,@RequestBody EmpLeave empLeave) {
	
		try {
			EmpLeave leave=empLeave;
			restTemplate.postForObject(url + "/applyLeave/"+empId, leave, EmpLeave.class);	
			response.put("Message", "Mail Sent successfully !!");
			response.put("status", "true");
			response.put("Result", "Success");
			log.info(response.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception ex) {
			throw new BadCredentialsException("Leave Not appleid !!");
		}
	}

	@PutMapping("responseMail/{empId}/{managerId}") 
	public Object responseMail(@PathVariable Long empId, @RequestBody EmpLeave empLeave, @PathVariable Long managerId) {
		try {
			EmpLeave leave=empLeave;
			restTemplate.patchForObject(url +"/responseMail/"+empId+"/"+managerId, leave, EmpLeave.class);	
			response.put("Message", " Response Mail Sent successfully By : "+managerId);
			response.put("status", "true");
			response.put("Result", "Success");
			log.info(response.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception ex) {
			throw new BadCredentialsException("Leave Not appleid !!");
		}
	}
}
