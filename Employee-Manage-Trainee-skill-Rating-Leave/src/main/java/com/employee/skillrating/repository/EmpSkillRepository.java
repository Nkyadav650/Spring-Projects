package com.employee.skillrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.skillrating.entity.EmpSkill;

public interface EmpSkillRepository extends JpaRepository<EmpSkill, Long> {

}
