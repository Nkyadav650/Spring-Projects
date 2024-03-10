package com.Eidiko.mailConfig;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.Eidiko.Service.EmailService;
import com.Eidiko.repository.MailCredentialsRepository;

import lombok.extern.slf4j.Slf4j;



@Configuration
@Slf4j
public class MailConfig  {

	@Autowired
	private MailCredentialsRepository mailCredentialsRepository;

	    @Bean
	    public JavaMailSender javaMailSender() {
	        return new JavaMailSenderImpl();
	    }

	    private JavaMailSenderImpl configureMailSender(String emailId) {
	    	log.info("inside MailConfig method-configurMailSender : {}",emailId);
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername(emailId);
	        mailSender.setPassword(mailCredentialsRepository.findByEmail(emailId).getPassword());

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        return mailSender;
	    }

	    

	    public JavaMailSender getMailSender(String emailId) {
	        return configureMailSender(emailId);
	    }
}


