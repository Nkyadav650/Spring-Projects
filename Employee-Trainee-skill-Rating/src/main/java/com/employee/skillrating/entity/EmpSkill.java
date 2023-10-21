package com.employee.skillrating.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmpSkill {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long skillId; 
	    private String skill;
	    private String working;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    @ManyToOne
	    @JoinColumn(name = "employe_id")
	    @JsonBackReference
	    private Employee employee;
	 
}
