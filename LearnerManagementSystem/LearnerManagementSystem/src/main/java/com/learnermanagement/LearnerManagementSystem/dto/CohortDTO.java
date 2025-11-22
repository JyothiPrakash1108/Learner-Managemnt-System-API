package com.learnermanagement.LearnerManagementSystem.dto;

import java.util.List;

public class CohortDTO {
    private Long id;
    private String cohortName;
    private String cohortDescription;
    private List<LearnerDTO> learners;
    public CohortDTO(){

    }
    public CohortDTO(Long id, String cohortName, String cohortDescription) {
        this.id = id;
        this.cohortName = cohortName;
        this.cohortDescription = cohortDescription;
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
    public List<LearnerDTO> getLearners() {
        return learners;
    }
    public void setLearners(List<LearnerDTO> learners) {
        this.learners = learners;
    }
}
