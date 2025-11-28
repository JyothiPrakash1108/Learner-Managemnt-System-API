package com.learnermanagement.LearnerManagementSystem.dto;

import java.util.List;


public class LearnerDTO {
    private Long id;
    private String name;
    private String email;
    private String linkedInProfile;
    private List<CohortDTO> cohorts; 
    public LearnerDTO(){

    }
    public LearnerDTO(Long id, String name, String email, List<CohortDTO> cohorts, String linkedInProfile) {
        this(id, name, email, cohorts);
        this.linkedInProfile = linkedInProfile;
    }
    public LearnerDTO(Long id, String name, String email, List<CohortDTO> cohorts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cohorts = cohorts;
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
    public List<CohortDTO> getCohorts() {
        return cohorts;
    }   
    public void setCohorts(List<CohortDTO> cohorts) {
        this.cohorts = cohorts;
    }
    public String getLinkedInProfile() {
        return linkedInProfile;
    }
    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }
}
