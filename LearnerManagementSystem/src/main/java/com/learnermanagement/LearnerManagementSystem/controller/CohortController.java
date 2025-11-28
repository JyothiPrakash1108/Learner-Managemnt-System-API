package com.learnermanagement.LearnerManagementSystem.controller;

import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTOs;
import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTOsWithLearners;

import java.util.List;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTOWithLearners;
import static com.learnermanagement.LearnerManagementSystem.mapper.LearnerMapper.toLearnerDTOs;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@RestController
public class CohortController {

    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/cohorts")
    public CohortDTO createCohort(@RequestBody Cohort cohort){
        return learnerManagementService.saveCohort(cohort);
    }
    @GetMapping("/cohorts")
    public List<CohortDTO> getCohorts(){
        List<Cohort> cohorts =  learnerManagementService.getAllCohorts();
        return toCohortDTOs(cohorts);
    }
    
    @PostMapping("/cohorts/{cohortId}/learners")
    public CohortDTO assignLearnersToCohort(@PathVariable Long cohortId,@RequestParam(value="learnerIds") List<Long> learnerIdsList) throws CohortNotFoundException, LearnerNotFoundException{
        var assignedCohort =  learnerManagementService.assignLearnersToCohort(cohortId,learnerIdsList);
        return toCohortDTOWithLearners(assignedCohort);
    }
    @GetMapping("/cohorts/{cohortId}/learners")
    public List<LearnerDTO> getLearnerInCohort(@PathVariable Long cohortId) throws CohortNotFoundException{
        List<Learner> learners = learnerManagementService.getLearnersInCohort(cohortId);
        return toLearnerDTOs(learners);
    }
    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity handleCohortNotFoundException(CohortNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
