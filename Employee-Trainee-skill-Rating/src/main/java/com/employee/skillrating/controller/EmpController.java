package com.employee.skillrating.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.employee.skillrating.entity.Employee;
import com.employee.skillrating.service.EmpService;

import jakarta.validation.Valid;

@RestController

public class EmpController {

	Logger logger= LoggerFactory.getLogger(EmpController.class);
    @Autowired
    private EmpService emailService;

    
    
    // for sending mail
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @ModelAttribute Employee emailRequest, BindingResult bindingResult,Long empId) {
        if (bindingResult.hasErrors()) {
        	
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        try {
            emailService.sendEmailWithAttachment(emailRequest, empId);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }
   
}
