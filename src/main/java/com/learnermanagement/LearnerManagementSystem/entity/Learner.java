package com.learnermanagement.LearnerManagementSystem.entity;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="learners")
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Email
    private String email;
    @URL
    private String linkedInProfile;
    @ManyToMany(mappedBy = "learners")
    private List<Cohort> cohorts = new ArrayList<>();
    public Learner(){

    }
    

    public Learner(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Cohort> getCohorts() {
        return cohorts;
    }
    public void setCohort(List<Cohort> cohort) {
        this.cohorts = cohort;
    }  
    public String getLinkedInProfile() {
        return linkedInProfile;
    }
    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }
}
