package com.Eidiko.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.Eidiko.Entity.EmailRequest;
import com.Eidiko.Entity.MailCredentials;
import com.Eidiko.mailConfig.MailConfig;
import com.Eidiko.repository.MailCredentialsRepository;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImp implements EmailService {



    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired 
    private MailCredentialsRepository mailCredentialsRepository;

    @Autowired
    private MailConfig mailConfiguration;

	
    @Override
    public void sendEmailWithAttachment(EmailRequest emailRequest) {
    //MailCredentials credentials = this.getMailCredentialsByEmail( emailRequest.getFrom());
    	log.info("inside EmailSericeImp method sendEmailWithAttachment : {}",emailRequest);
        try {
        	 JavaMailSender javaMailSender = mailConfiguration.getMailSender(emailRequest.getFrom());
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            // Create a Thymeleaf context and add variables
			
			  Context thymeleafContext = new Context();
			  thymeleafContext.setVariable("subject", emailRequest.getSubject());
			  thymeleafContext.setVariable("name", emailRequest.getName()); // Example name
			  thymeleafContext.setVariable("text", emailRequest.getText());
			  
			  // Process the HTML template with Thymeleaf
			  String htmlContent = templateEngine.process("templete", thymeleafContext);
			  
			  if (htmlContent != null) { helper.setText(htmlContent, true); } else {
			  System.err.println("Email template file not found or couldn't be read!");
			  return; }
			 
            if (emailRequest.getAttachment() != null && !emailRequest.getAttachment().isEmpty()) {
                helper.addAttachment(
                        Objects.requireNonNull(emailRequest.getAttachment().getOriginalFilename()),
                        new ByteArrayResource(emailRequest.getAttachment().getBytes())
                );
            }

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public MailCredentials createMailCredentails(MailCredentials credentials) {
		// TODO Auto-generated method stub
		return mailCredentialsRepository.save(credentials);
	}

	@Override
	public MailCredentials getMailCredentialsByEmail( String email) {
		// TODO Auto-generated method stub
		return mailCredentialsRepository.findByEmail(email);
	}
    
    
}
