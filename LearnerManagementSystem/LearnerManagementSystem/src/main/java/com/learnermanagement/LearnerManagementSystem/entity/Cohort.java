package com.learnermanagement.LearnerManagementSystem.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Cohort {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String cohortName;
    private String cohortDescription;
    @ManyToMany
    private List<Learner> learners;
    public Cohort(){

    }
    public Cohort(String cohortDescription, String cohortName, List<Learner> learners) {
        this.cohortDescription = cohortDescription;
        this.cohortName = cohortName;
        this.learners = learners;
    }

    public String getCohortName() {
        return cohortName;
    }
    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }
    public String getCohortDescription() {
        return cohortDescription;
    }
    public void setCohortDescription(String cohortDescription) {
        this.cohortDescription = cohortDescription;
    }
    public List<Learner> getLearners() {
        return learners;
    }
    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }
    
}
