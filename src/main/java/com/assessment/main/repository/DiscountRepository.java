package com.assessment.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.main.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

	
	@Query("from Discount where employeeType=:employeeType")
	Discount discountByUserType(@Param("employeeType") String employeeType);
}
