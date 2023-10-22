package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.model.Employee;
import com.employee.repo.EmployeeRepo;

@Controller
public class EmployeeController {
//@SuppressWarnings("unused")
@Autowired
private EmployeeRepo eRepo;

@RequestMapping("/")
public String empHome() {
	return "/emphome";
}
@RequestMapping("/eRegister")
public String eRegistration(Model model) {
	
	List<String>gender=new ArrayList<>();
	gender.add("Male");
	gender.add("Female");
	model.addAttribute("genders", gender);

	List<String>city=new ArrayList<>();
	city.add("hyderabad");
	city.add("delhi");
	city.add("mumbai");
	city.add("singrauli");
	city.add("pryagraj");
	model.addAttribute("city", city);

	List<String>state=new ArrayList<>();
	state.add("Madhya Pradesh");
	state.add("Uttar Pradesh");
	state.add("maharastra");
	state.add("bihar");
	state.add("west bangol");
	model.addAttribute("state", state);

	List<String>country=new ArrayList<>();
	country.add("India");
	country.add("banladesh");
	country.add("nepal");
	country.add("US");
	country.add("Rasia");
	
	model.addAttribute("country", country);
	return "/eRegistration";

}
@RequestMapping("/views")
public String empview() {
	return "/empview";
}
}
