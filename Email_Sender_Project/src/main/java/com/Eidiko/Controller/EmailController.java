package com.Eidiko.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Eidiko.Entity.EmailRequest;
import com.Eidiko.Entity.MailCredentials;
import com.Eidiko.Service.EmailService;


import jakarta.validation.Valid;

@RestController

public class EmailController {

	private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequest emailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        try {
            emailService.sendEmailWithAttachment(emailRequest);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }
    
    @PostMapping("/saveMailCredentials")
    public MailCredentials createMailCredentials(@RequestBody MailCredentials credentials) {
    	return emailService.createMailCredentails(credentials);
    }
    @GetMapping("/getMailCredentials/{email}")
    public MailCredentials getMailCredentials(@PathVariable String email) {
    	return emailService.getMailCredentialsByEmail(email);
    }
   
}
