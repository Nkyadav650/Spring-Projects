package com.employee.skillrating.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

  public class EmpRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empRatingId;
	private int month;
	private int year;
	private boolean flag;
	private String technology;
	private double techRating;
	private double communicationRating;
	@ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinColumn(name = "empId")
    @JsonBackReference
    private Employee employee;
	
	}
