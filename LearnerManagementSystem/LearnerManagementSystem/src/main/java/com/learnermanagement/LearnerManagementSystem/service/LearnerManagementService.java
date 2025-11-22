package com.learnermanagement.LearnerManagementSystem.service;

import java.util.ArrayList;
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

    public Learner fetchLearnerById(Long id) throws LearnerNotFoundException {
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

    public Cohort assignLearnersToCohort(Long cohortId, Long learnerId) throws CohortNotFoundException {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if(!optionalCohort.isPresent()){
            throw new CohortNotFoundException("cohort not found with id"+cohortId);
        }
        Optional<Learner> optionalLearner = learnerRepository.findById(learnerId);
        if(!optionalLearner.isPresent()){
            throw new CohortNotFoundException("learner not found with id"+learnerId);
        }

        Cohort cohort = optionalCohort.get();
        Learner learner = optionalLearner.get();
        cohort.getLearners().add(learner);
        return cohortRepository.save(cohort);
    }
    public Cohort assignAndCreateLearnersToCohort(Long cohortId, List<Learner> learners) throws CohortNotFoundException, LearnerNotFoundException {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if(!optionalCohort.isPresent()){
            throw new CohortNotFoundException("cohort not found with id"+cohortId);
        }
        Cohort cohort = optionalCohort.get();
        List<Learner> newLearners = new ArrayList<>();
        for(Learner learner : learners){
            Optional<Learner> optionalLearner = learnerRepository.findByEmail(learner.getEmail());
            if(optionalLearner.isPresent()){
                cohort.getLearners().add(optionalLearner.get());
            } else {
                newLearners.add(learner);
            }
        }
        cohort.getLearners().addAll(newLearners);
        return cohortRepository.save(cohort);
    }
}
