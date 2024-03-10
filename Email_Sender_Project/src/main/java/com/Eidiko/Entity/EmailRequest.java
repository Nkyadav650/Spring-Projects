 package com.Eidiko.Entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertFalse.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String from;
	private String to;
	@List(value = { @AssertFalse })
	private String cc;
	private String subject;
	private String text;
	private MultipartFile attachment;
	
}
