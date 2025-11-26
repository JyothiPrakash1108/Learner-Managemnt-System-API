package com.learnermanagement.LearnerManagementSystem.respositorytest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.CourseRepository;
import com.learnermanagement.LearnerManagementSystem.repository.LearnerRepository;

@DataJpaTest
public class RelationShipTests {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private CohortRepository cohortRepository;
    /*
      course (1) <--> (M) cohorts
     */
    @Test
    public void givenCourseWithMultipleCohorts_whenPersisted_thenCourseAndCohortsAreSaved(){
        Course course = new Course();
        course.setCourseName("Java Programming");
        course.setCourseDescription("Learn the basics of Java programming.");

        Cohort cohort1 = new Cohort();
        cohort1.setCohortName("Cohort A");
        cohort1.setCourse(course);
        Cohort cohort2 = new Cohort();
        cohort2.setCohortName("Cohort B");
        cohort2.setCourse(course);

        course.getCohorts().addAll(List.of(cohort1, cohort2));

        Course savedCourse = courseRepository.save(course);

        assertThat(savedCourse.getId()).isNotNull();
        assertThat(savedCourse.getCohorts()).hasSize(2);
        assertThat(savedCourse.getCohorts().get(0).getCourse().equals(savedCourse));
        assertThat(savedCourse.getCohorts().get(0).getCohortName()).isEqualTo(cohort1.getCohortName());

    }
    /*
        cohort (M) --> (1) course
    */
    @Test
    public void givenCohortAssignedToCourse_whenSavingCohort_thenCohortReferenceCourse(){
        Course course = new Course();
        course.setCourseName("Python Programming");
        course.setCourseDescription("Learn the basics of Python programming.");
        courseRepository.save(course);
        Cohort cohort = new Cohort();
        cohort.setCohortName("Cohort C");
        cohort.setCourse(course);
        Cohort savedCohort = cohortRepository.save(cohort);
        assertThat(savedCohort.getId()).isNotNull();
        assertThat(savedCohort.getCourse().getId()).isEqualTo(course.getId());

    }
    @Test
    public void givenCohortWithMultipleLearners_whenSavingCohort_thenJoinTableIsPersisted(){
        Cohort cohort = new Cohort();
        cohort.setCohortName("Cohort D");

        Learner learner1 = new Learner();
        learner1.setName("Alice");
        Learner learner2 = new Learner();
        learner2.setName("Bob");
        learnerRepository.saveAll(List.of(learner1, learner2));

        cohort.setLearners(List.of(learner1,learner2));

        Cohort savedCohort = cohortRepository.save(cohort);
        
        assertThat(savedCohort.getId()).isNotNull();
        assertThat(savedCohort.getLearners()).hasSize(2);
        assertThat(savedCohort.getLearners().stream().anyMatch(learner -> learner.getName().equals("Alice"))).isTrue();
    }
}
