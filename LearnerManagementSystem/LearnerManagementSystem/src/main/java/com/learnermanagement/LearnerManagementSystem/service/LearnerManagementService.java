package com.learnermanagement.LearnerManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

@Service
public class LearnerManagementService {
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private CohortRepository cohortRepository;

    public Learner save(Learner learner) {
        return learnerRepository.save(learner);
    }

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Learner fetchLearnerById(int id) throws LearnerNotFoundException {
        Optional<Learner> optionalLearner = learnerRepository.findById(id);
        if(optionalLearner.isPresent()){
            return optionalLearner.get();
        }
        throw new LearnerNotFoundException("learner not found");
    }

    public Learner fetchLearnerByName(String name){
         return learnerRepository.findByName(name);
    }

    public Cohort saveCohort(Cohort cohort) {
        return cohortRepository.save(cohort);
    }

    public List<Cohort> getAllCohorts(){
        return cohortRepository.findAll();
    }

    public Cohort assignLearnersToCohort(int cohortId, Long learnerId) throws CohortNotFoundException {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if(!optionalCohort.isPresent()){
            throw new CohortNotFoundException("cohort not found with id"+cohortId);
        }
        Optional<Learner> optionalLearner = lear
    }
}
