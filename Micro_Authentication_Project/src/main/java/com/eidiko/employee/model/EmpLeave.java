package com.eidiko.employee.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.eidiko.employee.enums.EnumStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmpLeave {
	
	private long leaveId;
	private long empId;
	private String leaveReason;
	private long createdBy;
	private LocalDateTime createDate=LocalDateTime.now();
	private LocalDate startDate;
	private LocalDate endDate;
	private long approvedBy;
	private String remarks;
	
	  @Enumerated(EnumType.STRING)
	  private EnumStatus status = EnumStatus.PENDING;
	 
	private String leaveType;

}
