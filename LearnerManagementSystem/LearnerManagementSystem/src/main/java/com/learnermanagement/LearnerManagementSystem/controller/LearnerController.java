package com.learnermanagement.LearnerManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@RestController
@SuppressWarnings("UnnecessaryReturnStatement")
public class LearnerController {
    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/learners")
    public Learner createLearner(@RequestBody Learner learner){
        return learnerManagementService.save(learner);
    }

    @GetMapping("/learners")
    public List<Learner> fetchAllLearners(){
        return learnerManagementService.getAllLearners();
    }

    @GetMapping("/learners/{id}")
    public Learner fetchLearnerById(@PathVariable("id") int id) throws LearnerNotFoundException{
        return learnerManagementService.fetchLearnerById(id);
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
