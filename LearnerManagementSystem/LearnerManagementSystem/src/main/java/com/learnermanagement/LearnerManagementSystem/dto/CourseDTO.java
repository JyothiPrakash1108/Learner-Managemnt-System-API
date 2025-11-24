package com.learnermanagement.LearnerManagementSystem.dto;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CourseDTO {
    private Long id;
    private String courseName;
    private String courseDescription;
    private List<CohortDTO> cohortDTOS;

    public CourseDTO(){

    }

    public CourseDTO(Long id, String courseName, String courseDescription, List<CohortDTO> cohorts) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.cohortDTOS = cohorts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<CohortDTO> getCohorts() {
        return cohortDTOS;
    }

    public void setCohorts(List<CohortDTO> cohorts) {
        this.cohortDTOS = cohorts;
    }
}
