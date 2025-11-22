package com.learnermanagement.LearnerManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
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
    /* 
    @PostMapping("/cohorts/{cohortId}/learners")
    public Cohort assignLearnersToCohort(@PathVariable Long cohortId,@RequestParam(value="learnerId" , required=false) Long learnerId) throws CohortNotFoundException{
        return learnerManagementService.assignLearnersToCohort(cohortId,learnerId);
    }*/
    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity handleCohortNotFoundException(CohortNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    public Cohort assignAndCreateLearnersToCohort(Long cohortId,List<Learner> learners) throws CohortNotFoundException, LearnerNotFoundException {
        return learnerManagementService.assignAndCreateLearnersToCohort(cohortId, learners);
    }
}
