package com.learnermanagement.LearnerManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

@Service
public class LearnerManagementService {
    @Autowired
    private LearnerRepository learnerRepository;

    public Learner save(Learner learner) {
        return learnerRepository.save(learner);
    }

}
