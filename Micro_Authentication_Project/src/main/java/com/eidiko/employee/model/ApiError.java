package com.eidiko.employee.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

	public String message;
	public List<String> details;
	public HttpStatusCode status;
	public LocalDateTime timeStamp;
}
