package com.learnermanagement.LearnerManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTO;

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
    public List<Cohort> fetchCohortsByLearnerId(Long learnerId) throws LearnerNotFoundException {
        Optional<Learner> optionalLearner = learnerRepository.findById(learnerId);
        if(optionalLearner.isPresent()){
            Learner learner = optionalLearner.get();
            return learner.getCohorts();
        }
        throw new LearnerNotFoundException("learner not found with ID "+ learnerId);
    }
    public Learner fetchLearnerByName(String name){
         return learnerRepository.findByName(name);
    }



    public List<Cohort> getAllCohorts(){
        return cohortRepository.findAll();
    }

    public Cohort assignLearnersToCohort(Long cohortId, List<Long> learnerIdsList) throws CohortNotFoundException, LearnerNotFoundException {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if(!optionalCohort.isPresent()){
            throw new CohortNotFoundException("cohort not found with id : "+cohortId);
        }
        List<Learner> learnersToAdd = new ArrayList<>();
        for(Long learnerId : learnerIdsList){
            Optional<Learner> optionalLearner = learnerRepository.findById(learnerId);
            if(!optionalLearner.isPresent()){
                throw new LearnerNotFoundException("learner not found with id : "+learnerId);
            }
            Learner learner = optionalLearner.get();
            learnersToAdd.add(learner);
        }

        Cohort cohort = optionalCohort.get();
        cohort.getLearners().addAll(learnersToAdd);
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

    public CohortDTO saveCohort(Cohort cohort) {
        return toCohortDTO(cohortRepository.save(cohort));
    }

    public Learner updateLearner(Learner learner, Long learnerId) throws LearnerNotFoundException {
        Optional<Learner> optionalLearner = learnerRepository.findById(learnerId);
        if(!optionalLearner.isPresent()){
            throw new LearnerNotFoundException("learner not found with id"+learnerId);
        }
        Learner existingLearner = optionalLearner.get();
        if(learner.getName() != null){
            existingLearner.setName(learner.getName());
        }
        if(learner.getEmail() != null){
            existingLearner.setEmail(learner.getEmail());
        }
        if(learner.getLinkedInProfile() != null){
            existingLearner.setLinkedInProfile(learner.getLinkedInProfile());
        }
        return learnerRepository.save(existingLearner);
    }

    public List<Learner> getLearnersInCohort(Long cohortId) throws CohortNotFoundException {
        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if(!optionalCohort.isPresent()){
            throw new CohortNotFoundException("Cohort not found with id : "+cohortId);
        }
        Cohort cohort = optionalCohort.get();
        return cohort.getLearners();
    }
}
