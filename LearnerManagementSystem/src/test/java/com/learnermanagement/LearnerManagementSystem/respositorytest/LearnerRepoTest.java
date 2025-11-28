package com.learnermanagement.LearnerManagementSystem.respositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

import javax.sql.DataSource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LearnerRepoTest {
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private DataSource dataSource;
    @Test
    public void printDbUrl() throws Exception {
        System.out.println(">>> DB URL = " +  dataSource.getConnection().getMetaData().getURL());
        System.out.println(">>> DB User = " + dataSource.getConnection().getMetaData().getUserName());
    }
    @Test
    public void testSaveLearnerSucessfully() {
        Learner learner = new Learner();
        learner.setName("John Doe");
        learner.setEmail("johndoe@gmail.com");
        Learner savedLearner = learnerRepository.save(learner);
        assertEquals("John Doe", savedLearner.getName());
        assertEquals("johndoe@gmail.com", savedLearner.getEmail());
    }
    @Test
    public void testFindLearnerByName() {
        Learner learner = new Learner();
        learner.setName("john");
        learner.setEmail("john@gmail.com");
        learnerRepository.save(learner);

        Learner foundLearner = learnerRepository.findByName("john");
        assertEquals("john", foundLearner.getName());
        assertEquals("john@gmail.com", foundLearner.getEmail());
}
