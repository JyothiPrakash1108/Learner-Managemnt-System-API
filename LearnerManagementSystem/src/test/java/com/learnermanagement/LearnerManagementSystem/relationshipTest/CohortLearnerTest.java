package com.learnermanagement.LearnerManagementSystem.relationshipTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
public class CohortLearnerTest {
    @Autowired
    private CohortRepository cohortRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Test
    public void givenCohortWithMultipleLearners_whenPersisted_thenCohortAndLearnersAreSaved(){
        Cohort cohort = new Cohort();
        cohort.setCohortName("Cohort A");
        cohort.setCourse(null); 
        cohort.setCohortDescription("Cohort A Starts from dec 2025");

        Learner learner1 = new Learner();
        learner1.setName("Alice");
        learner1.setEmail("alice@gmail.com");
        Learner learner2 = new Learner();
        learner2.setName("Bob");
        learner2.setEmail("bob@gmail.com");
        cohort.getLearners().add(learner1);
        cohort.getLearners().add(learner2);

        Cohort savedCohort = cohortRepository.save(cohort);

        assertThat(savedCohort.getId()).isNotNull();
        assertThat(savedCohort.getLearners()).hasSize(2);
        assertThat(savedCohort.getLearners().get(0).getName()).isEqualTo(learner1.getName());

    }
}
