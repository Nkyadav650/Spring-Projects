package com.employee.skillrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.skillrating.entity.EmpRating;
@Repository
public interface EmpRatingRepository extends JpaRepository<EmpRating, Long> {

}
