package com.assessment.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.main.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
