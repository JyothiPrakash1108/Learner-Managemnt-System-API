package com.learnermanagement.LearnerManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@RestController
public class LearnerController {
    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/learners")
    public Learner createLearner(@RequestBody Learner learner){
        return learnerManagementService.save(learner);
    }
}
