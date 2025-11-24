package com.learnermanagement.LearnerManagementSystem.mapper;

import java.util.ArrayList;
import java.util.List;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;

public class LearnerMapper {
    public static LearnerDTO toLearnerDTO(Learner learner){
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setId(learner.getId());
        learnerDTO.setName(learner.getName());  
        learnerDTO.setEmail(learner.getEmail());
        learnerDTO.setLinkedInProfile(learner.getLinkedInProfile());
        List<CohortDTO> cohortDTOs = new ArrayList<>();
       /*  for(Cohort cohort : learner.getCohorts()){
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            cohortDTO.setLearners(null);
            cohortDTOs.add(cohortDTO);
        }*/
        learnerDTO.setCohorts(cohortDTOs);
        return learnerDTO;
    }
     public static LearnerDTO toLearnerDTOWithCohorts(Learner learner){
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setId(learner.getId());
        learnerDTO.setName(learner.getName());  
        learnerDTO.setEmail(learner.getEmail());
        learnerDTO.setLinkedInProfile(learner.getLinkedInProfile());
        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for(Cohort cohort : learner.getCohorts()){
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            cohortDTO.setLearners(new ArrayList<>() );
            cohortDTOs.add(cohortDTO);
        }
        learnerDTO.setCohorts(cohortDTOs);
        return learnerDTO;
    }
    public static List<LearnerDTO> toLearnerDTOs(List<Learner> learners){
       List<LearnerDTO> learnerDTOs = new ArrayList<>();
       for(Learner learner : learners){
            LearnerDTO learnerDTO = new LearnerDTO();
            learnerDTO.setId(learner.getId());
            learnerDTO.setName(learner.getName());  
            learnerDTO.setEmail(learner.getEmail());
            List<CohortDTO> cohortDTOs = new ArrayList<>();
            learnerDTO.setCohorts(cohortDTOs);
            learnerDTOs.add(learnerDTO);
        }

        return learnerDTOs;
    }
    public static List<LearnerDTO> toLearnerDTOsWithCohorts(List<Learner> learners){
       List<LearnerDTO> learnerDTOs = new ArrayList<>();
       for(Learner learner : learners){
            LearnerDTO learnerDTO = new LearnerDTO();
            learnerDTO.setId(learner.getId());
            learnerDTO.setName(learner.getName());  
            learnerDTO.setEmail(learner.getEmail());
            List<CohortDTO> cohortDTOs = new ArrayList<>();
           for(Cohort cohort : learner.getCohorts()){
                CohortDTO cohortDTO = new CohortDTO();
                cohortDTO.setId(cohort.getId());
                cohortDTO.setCohortName(cohort.getCohortName());
                cohortDTO.setCohortDescription(cohort.getCohortDescription());
                cohortDTO.setLearners(new ArrayList<>());
                cohortDTOs.add(cohortDTO);
            }
            learnerDTO.setCohorts(cohortDTOs);
            learnerDTOs.add(learnerDTO);
        }

        return learnerDTOs;
    }
}
