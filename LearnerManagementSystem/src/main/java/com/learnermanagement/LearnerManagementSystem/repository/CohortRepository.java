package com.learnermanagement.LearnerManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;

@Repository
public interface CohortRepository extends JpaRepository<Cohort,Long>{
}
