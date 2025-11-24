package com.learnermanagement.LearnerManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTOs;

import static com.learnermanagement.LearnerManagementSystem.mapper.LearnerMapper.toLearnerDTO;
import static com.learnermanagement.LearnerManagementSystem.mapper.LearnerMapper.toLearnerDTOWithCohorts;
import static com.learnermanagement.LearnerManagementSystem.mapper.LearnerMapper.toLearnerDTOs;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

import jakarta.validation.Valid;

@RestController
public class LearnerController {
    @Autowired
    private LearnerManagementService learnerManagementService;
    /*
     -> Create a learner
     -> get all learners
     -> get learner by id
     -> 
     */
    @PostMapping("/learners")
    public LearnerDTO createLearner(@Valid @RequestBody Learner learner){
    
        Learner savedLearner = learnerManagementService.save(learner);
        return toLearnerDTO(savedLearner);
    }
    @GetMapping("/learners")
    public List<LearnerDTO> fetchAllLearners(){
        return toLearnerDTOs(learnerManagementService.getAllLearners());
    }

    @GetMapping("/learners/{id}")
    public LearnerDTO fetchLearnerById(@PathVariable("id") Long id) throws LearnerNotFoundException{
        Learner existingLearner = learnerManagementService.fetchLearnerById(id);
        return toLearnerDTOWithCohorts(existingLearner);
    }
    @GetMapping("/learners/{id}/cohorts")
    public List<CohortDTO> fetchCohortByLearnerId(@PathVariable("id") Long learnerId) throws LearnerNotFoundException{
        List<Cohort> cohorts = learnerManagementService.fetchCohortsByLearnerId(learnerId);
        return toCohortDTOs(cohorts);
    }
    @PatchMapping("/learners/{id}")
    public LearnerDTO updateLearner(@RequestBody Learner learner,@PathVariable("id") Long learnerId) throws LearnerNotFoundException{
        Learner updatedLearner = learnerManagementService.updateLearner(learner, learnerId);
        return toLearnerDTO(updatedLearner);
    }
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
