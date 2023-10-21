package com.employee.skillrating.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.employee.skillrating.entity.EmpRating;
import com.employee.skillrating.entity.Employee;
import com.employee.skillrating.repository.EmpRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmpServiceImp implements EmpService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private EmpRepository empRepo;
    
    
    @Override
    public void sendEmailWithAttachment(Employee emailRequest,Long empId) {
    	String subject="Regarding skill Rating";
    	String text="";
    	Employee employee=empRepo.findById(empId).get();
    	EmpRating rating=(EmpRating) employee.getEmpRating();
    	
    	if(rating.getTechRating()<2.5) {
    		
    	}else if(rating.getCommunicationRating()<2.5) {
    		
    	}
    	
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(emailRequest.getEmail());
            helper.setSubject(subject);

            // Create a Thymeleaf context and add variables
            Context thymeleafContext = new Context();
            thymeleafContext.setVariable("subject", subject);
            thymeleafContext.setVariable("name", emailRequest.getName()); // Example name
            thymeleafContext.setVariable("text", text);
            
            // Process the HTML template with Thymeleaf
            String htmlContent = templateEngine.process("templete", thymeleafContext);

            if (htmlContent != null) {
                helper.setText(htmlContent, true);
            } else {
                System.err.println("Email template file not found or couldn't be read!");
                return;
            }

			
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	@Override
	public List<Employee> getAll() {
		
		//Calendar col=Calendar.getInstance();
		 
		List<Employee>employee=empRepo.findAll().stream().filter((emp)->emp.getEmpRating()
								.stream().allMatch(month->month.getMonth()==Calendar.MONTH))
								.collect(Collectors.toList());
	
		return employee;
	}
}
