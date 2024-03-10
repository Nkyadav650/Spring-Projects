package com.eidiko.employee.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveType {
	private long id;
	private String createdBy;
	private LocalDate createDate;
	private String leaveCode;

}
