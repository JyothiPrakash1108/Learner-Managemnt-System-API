<<<<<<< HEAD
package com.learnermanagement.LearnerManagementSystem.mapper;

import java.util.ArrayList;
import java.util.List;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;

public class CohortMapper {
    public static CohortDTO toCohortDTO(Cohort cohort) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
           /* List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }*/
        cohortDTO.setLearners(new ArrayList<>());
            return cohortDTO;
    }
    public static CohortDTO toCohortDTOWithLearners(Cohort cohort) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
           List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }
        cohortDTO.setLearners(new ArrayList<>());
            return cohortDTO;
    }
    public static List<CohortDTO> toCohortDTOs(List<Cohort> cohorts) {
        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for (Cohort cohort : cohorts) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            List<LearnerDTO> learnerDTOs = new ArrayList<>();
          /*   for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }*/
            
            cohortDTO.setLearners(new ArrayList<>());
            
            cohortDTOs.add(cohortDTO);
        }
        return cohortDTOs;
    }
    public static List<CohortDTO> toCohortDTOsWithLearners(List<Cohort> cohorts) {
        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for (Cohort cohort : cohorts) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }
            if(learnerDTOs.isEmpty()){
                cohortDTO.setLearners(new ArrayList<>());
            }
            else{
                cohortDTO.setLearners(learnerDTOs);
            }
            cohortDTOs.add(cohortDTO);
        }
        return cohortDTOs;
    }
}
=======
package com.learnermanagement.LearnerManagementSystem.mapper;

import java.util.ArrayList;
import java.util.List;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;

public class CohortMapper {
    public static CohortDTO toCohortDTO(Cohort cohort) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
           /* List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }*/
        cohortDTO.setLearners(new ArrayList<>());
            return cohortDTO;
    }
    public static CohortDTO toCohortDTOWithLearners(Cohort cohort) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
           List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }
        cohortDTO.setLearners(new ArrayList<>());
            return cohortDTO;
    }
    public static List<CohortDTO> toCohortDTOs(List<Cohort> cohorts) {
        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for (Cohort cohort : cohorts) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            List<LearnerDTO> learnerDTOs = new ArrayList<>();
          /*   for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }*/
            
            cohortDTO.setLearners(new ArrayList<>());
            
            cohortDTOs.add(cohortDTO);
        }
        return cohortDTOs;
    }
    public static List<CohortDTO> toCohortDTOsWithLearners(List<Cohort> cohorts) {
        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for (Cohort cohort : cohorts) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            List<LearnerDTO> learnerDTOs = new ArrayList<>();
            for (Learner learner : cohort.getLearners()) {
                LearnerDTO learnerDTO = new LearnerDTO();
                learnerDTO.setId(learner.getId());
                learnerDTO.setName(learner.getName());
                learnerDTO.setEmail(learner.getEmail());
                learnerDTO.setCohorts(new ArrayList<>());
                learnerDTOs.add(learnerDTO);
            }
            if(learnerDTOs.isEmpty()){
                cohortDTO.setLearners(new ArrayList<>());
            }
            else{
                cohortDTO.setLearners(learnerDTOs);
            }
            cohortDTOs.add(cohortDTO);
        }
        return cohortDTOs;
    }
}
>>>>>>> fe99840de0a1cc86501a349676a2aff64b35115a
