package com.learnermanagement.LearnerManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@RestController
public class CohortController {

    @Autowired
    private LearnerManagementService learnerManagementService;
    @PostMapping("/cohorts")
    public Cohort createCohort(@RequestBody Cohort cohort){
        return learnerManagementService.saveCohort(cohort);
    }
    @GetMapping("/cohorts")
    public List<Cohort> getCohorts(){
        return learnerManagementService.getAllCohorts();
    }
    @PostMapping("/cohortd/{cohortId}/learners")
    public Cohort assignLearnersToCohort(@PathVariable int cohortId,@RequestParam(value="learnerId" , required=false) Long learnerId){
        return learnerManagementService.assignLearnersToCohort(cohortId,learnerId);
    }
}
