package com.learnermanagement.LearnerManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnermanagement.LearnerManagementSystem.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
