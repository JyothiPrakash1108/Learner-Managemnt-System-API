<<<<<<< HEAD
package com.learnermanagement.LearnerManagementSystem.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@ExtendWith(MockitoExtension.class)
public class LearnerManagementServiceTest {
    @InjectMocks
    private LearnerManagementService learnerManagementService;
    @Mock
    private LearnerRepository learnerRepository;

    @Test   
    public void givenLearnerData_whenSavingLearner_thenLearnerIsSavedSuccessfully() {
        Learner learner = new Learner();
        learner.setName("John Doe");
        learner.setEmail("johndoe@gmail.com");
        when(learnerRepository.save(learner)).thenReturn(learner);
        Learner savedLearner = learnerManagementService.save(learner);
        assertEquals("John Doe", savedLearner.getName());
        assertEquals("johndoe@gmail.com", savedLearner.getEmail());
    }
    @Test
    public void givenLearnersExist_whenGetAllLearners_thenReturnLearnerList() {
        Learner learner1 = new Learner();
        learner1.setName("John Doe");
        learner1.setEmail("john@gmail.com");
        Learner learner2 = new Learner();
        learner2.setName("Jane Smith");
        learner2.setEmail("jane@gmail.com");
        when(learnerRepository.findAll()).thenReturn(Arrays.asList(learner1, learner2));
        List<Learner> learners = learnerManagementService.getAllLearners();
        assertEquals(2, learners.size());
        assertEquals("John Doe", learners.get(0).getName());
        assertEquals("Jane Smith", learners.get(1).getName());
    }
    @Test
    public void givenLearnerId_whenFetchLearnerById_thenReturnLearner() throws LearnerNotFoundException{
        Learner learner1 = new Learner();
        learner1.setId(1L);
        learner1.setName("John Doe");
        learner1.setEmail("john@gmail.com");
        when(learnerRepository.findById(1L)).thenReturn(Optional.of(learner1));
        Learner fetchedLearner = learnerManagementService.fetchLearnerById(1L);
    
        assertEquals("John Doe", fetchedLearner.getName());
    }
    @Test
    public void givenNonExistingLearnerId_whenFetchLearnerById_thenThrowNotFoundException() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.empty());
        try {
            learnerManagementService.fetchLearnerById(1L);
        } catch (LearnerNotFoundException e) {
            assertEquals("learner not found", e.getMessage());
        }
    }

}
=======
package com.learnermanagement.LearnerManagementSystem.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@ExtendWith(MockitoExtension.class)
public class LearnerManagementServiceTest {
    @InjectMocks
    private LearnerManagementService learnerManagementService;
    @Mock
    private LearnerRepository learnerRepository;

    @Test   
    public void testSaveLearnerSucessfully() {
        Learner learner = new Learner();
        learner.setName("John Doe");
        learner.setEmail("johndoe@gmail.com");
        when(learnerRepository.save(learner)).thenReturn(learner);
        Learner savedLearner = learnerManagementService.save(learner);
        assertEquals("John Doe", savedLearner.getName());
        assertEquals("johndoe@gmail.com", savedLearner.getEmail());
    }
    @Test
    public void testGetAllLearnersSuccessfully() {
        Learner learner1 = new Learner();
        learner1.setName("John Doe");
        learner1.setEmail("john@gmail.com");
        Learner learner2 = new Learner();
        learner2.setName("Jane Smith");
        learner2.setEmail("jane@gmail.com");
        when(learnerRepository.findAll()).thenReturn(Arrays.asList(learner1, learner2));
        List<Learner> learners = learnerManagementService.getAllLearners();
        assertEquals(2, learners.size());
        assertEquals("John Doe", learners.get(0).getName());
        assertEquals("Jane Smith", learners.get(1).getName());
    }
    public void testFetchLearnerByIdSucessfully() throws LearnerNotFoundException{
        Learner learner1 = new Learner();
        learner1.setId(1L);
        learner1.setName("John Doe");
        learner1.setEmail("john@gmail.com");
        when(learnerRepository.findById(1L)).thenReturn(Optional.of(learner1));
        Learner fetchedLearner = learnerManagementService.fetchLearnerById(1L);
    
        assertEquals("John Doe", fetchedLearner.getName());
    }
    @Test
    public void testFetchLearnerByIdNotFound() {
        when(learnerRepository.findById(1L)).thenReturn(Optional.empty());
        try {
            learnerManagementService.fetchLearnerById(1L);
        } catch (LearnerNotFoundException e) {
            assertEquals("learner not found", e.getMessage());
        }
    }
}
>>>>>>> fe99840de0a1cc86501a349676a2aff64b35115a
