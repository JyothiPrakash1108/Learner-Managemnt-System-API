<<<<<<< HEAD
package com.learnermanagement.LearnerManagementSystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Cohort {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String cohortName;
    private String cohortDescription;
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE   })
    private List<Learner> learners = new ArrayList<>();
    @ManyToOne
    private Course course;  
    public Cohort(){

    }
    public Cohort(String cohortDescription, String cohortName, List<Learner> learners) {
        this.cohortDescription = cohortDescription;
        this.cohortName = cohortName;
        this.learners = learners;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
=======
package com.learnermanagement.LearnerManagementSystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Cohort {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String cohortName;
    private String cohortDescription;
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE   })
    private List<Learner> learners = new ArrayList<>();
    @ManyToOne
    private Course course;  
    public Cohort(){

    }
    public Cohort(String cohortDescription, String cohortName, List<Learner> learners) {
        this.cohortDescription = cohortDescription;
        this.cohortName = cohortName;
        this.learners = learners;
    }
    public Long getId() {
        return id;
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
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
>>>>>>> fe99840de0a1cc86501a349676a2aff64b35115a
