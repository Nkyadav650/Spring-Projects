package com.employee.skillrating.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.employee.skillrating.entity.EmpRating;
import com.employee.skillrating.entity.Employee;
import com.employee.skillrating.exception.IdNotFoundException;
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
	public Employee saveEmp(Employee emp) {
		return empRepo.save(emp);
	}

	@Override
	// @Scheduled(cron = "0 */1 * * * ?")
	public void sendEmailWithAttachment() throws Exception {
		// Employee email
		String subject = "Regarding skill Rating";
		try {
			List<Employee> emp = this.getAll();
			for (Employee employee : emp) {
				String text = "";
				EmpRating rating = employee.getEmpRating().get(employee.getEmpRating().size() - 1);

				if (rating.getTechRating() < 2.5 && rating.getCommunicationRating() < 2.5) {
					text = "your tech rating is low please mprove it";
				} else if (rating.getCommunicationRating() < 2.5) {
					text = "your communication rating is low please mprove it";
				} else if (rating.getTechRating() < 2.5) {
					text = "your tech rating is low please mprove it";
				}

				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setTo(employee.getEmail());
				helper.setSubject(subject);

				// Create a Thymeleaf context and add variables
				Context thymeleafContext = new Context();
				thymeleafContext.setVariable("subject", subject);
				thymeleafContext.setVariable("name", employee.getName()); // Example name
				thymeleafContext.setVariable("text", text);

				// Process the HTML template with Thymeleaf
				String htmlContent = templateEngine.process("templete", thymeleafContext);

				if (htmlContent != null) {
					helper.setText(htmlContent, true);
				} else {
					System.err.println("Email template file not found or couldn't be read!");

				}

				javaMailSender.send(message);
				System.out.println("Email Sent");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Employee> getAll() throws Exception {
		List<Employee> employee = empRepo.findEmployeesWithFilteredConditions();
		List<Employee> emps = employee.stream()
				.filter(emp -> emp.getEmpRating().stream().allMatch(rating -> rating.getMonth() == Calendar.MONTH))
				.collect(Collectors.toList());
		if (emps != null) {
			return emps;

		} else {
			throw new Exception("Employee Details Null !!");
		}
	}

	@Override
	public Employee updateEmp(Employee emp, Long empId) {
		Employee employee = empRepo.findById(empId).orElseThrow(() -> new IdNotFoundException("Id Not Available !!"));
		return empRepo.save(employee);
	}

	@Override
	public void deleteEmp(Long empId) {
		Employee employee = empRepo.findById(empId).orElseThrow(() -> new IdNotFoundException("Id Not Available !!"));
		empRepo.delete(employee);
	}

	@Override
	public Employee getEmp(Long empId) {
		return empRepo.findById(empId).orElseThrow(() -> new IdNotFoundException("Id Not Available !!"));
	}

}
