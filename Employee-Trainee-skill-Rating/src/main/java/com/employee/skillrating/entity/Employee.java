package com.employee.skillrating.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	 @Id
	  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String name;
	    private String email;
	    private LocalDate dateJoin;
	    private String mobile;
	    private String status; // active or inactive
	    private boolean flag;
	    private String about;
	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	    @JsonManagedReference
	    private List<EmpSkill> skill;
	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	    @JsonManagedReference
	    private List<EmpRating> empRating;
}
