package com.learnermanagement.LearnerManagementSystem.serviceTest;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.CourseNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.repository.CourseRepository;
import com.learnermanagement.LearnerManagementSystem.service.CourseService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CohortRepository cohortRepository;
    
    // this test does actually doesn't have any meaning full logic to test , but only delegates the call to repository save method.
    @Test 
    public void createCourse_whenNewCourse_returnSavedCourse() {
        //Arrange
        Course course = new Course();
        course.setCourseName("Backend Development  with Java");
        course.setCourseDescription("Learn backend development using Spring Boot and Hibernate");
        when(courseRepository.save(course)).thenReturn(course);
        
        //Act
        CourseDTO savedCourse = courseService.createCourse(course);


        //Assert
        assertEquals(course.getCourseName(), savedCourse.getCourseName());
        assertEquals(course.getCourseDescription(), savedCourse.getCourseDescription());
    }
    @Test
    public void givenCourseId_whenGetCourseById_thenReturnCourse() throws CourseNotFoundException {
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Backend Development  with Java");
        course.setCourseDescription("Learn backend development using Spring Boot and Hibernate");
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        CourseDTO savedCourse = courseService.getCourseById(courseId);

        assertEquals(course.getCourseName(),savedCourse.getCourseName());
        assertEquals(course.getId(),savedCourse.getId());
    }
    @Test
    public void givenInvalidCourseId_whenGetCourseById_thenThrowException() {
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Backend Development  with Java");
        course.setCourseDescription("Learn backend development using Spring Boot and Hibernate");
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        try {
            CourseDTO savedCourse = courseService.getCourseById(courseId);
        } catch (CourseNotFoundException e) {
          assertEquals("course not found with ID : "+courseId,e.getMessage());
        }
    }
    @Test
    void givenValidCourseIdAndCohorts_whenAssignCohortsToCourse_thenReturnUpdatedCourseDTO()
            throws Exception {

        Long courseId = 1L;
        List<Long> cohortIds = Arrays.asList(10L, 20L);

        Course course = new Course();
        course.setId(courseId);

        Cohort cohort1 = new Cohort();
        cohort1.setId(10L);

        Cohort cohort2 = new Cohort();
        cohort2.setId(20L);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(cohortRepository.findById(10L)).thenReturn(Optional.of(cohort1));
        when(cohortRepository.findById(20L)).thenReturn(Optional.of(cohort2));
        when(courseRepository.save(course)).thenReturn(course);

        CourseDTO result = courseService.assignCohortsTOCourses(courseId, cohortIds);

        assertNotNull(result);
        assertEquals(courseId, result.getId());
    }


    @Test
    void givenInvalidCourseId_whenAssignCohortsToCourse_thenThrowCourseNotFoundException() {

        Long courseId = 1L;

        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        try {
            courseService.assignCohortsTOCourses(courseId, Arrays.asList(10L));
        } catch (CourseNotFoundException | CohortNotFoundException e) {
            assertEquals("course not found with ID : "+courseId,e.getMessage());
        }
    }

    @Test
    void givenInvalidCohortId_whenAssignCohortsToCourse_thenThrowCohortNotFoundException()
            throws Exception {

        Long courseId = 1L;
        Long invalidCohortId = 99L;

        Course course = new Course();
        course.setId(courseId);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(cohortRepository.findById(invalidCohortId)).thenReturn(Optional.empty());

        try {
            courseService.assignCohortsTOCourses(courseId, Arrays.asList(invalidCohortId));
        } catch (CohortNotFoundException e) {
            assertEquals("cohort not found with ID : "+invalidCohortId,e.getMessage());
        }
    }


    @Test
    void givenValidCourseId_whenGetCohortsInCourse_thenReturnCohorts()
            throws Exception {

        Long courseId = 1L;

        Course course = new Course();
        course.setId(courseId);

        Cohort c1 = new Cohort();
        Cohort c2 = new Cohort();
        course.setCohorts(Arrays.asList(c1, c2));

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        List<Cohort> cohorts = courseService.getCohortsInCourse(courseId);

        assertEquals(2, cohorts.size());
    }

    @Test
    void givenInvalidCourseId_whenGetCohortsInCourse_thenThrowCourseNotFoundException() {
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());
        try {
            courseService.getCohortsInCourse(courseId);
        } catch (CourseNotFoundException e) {
            assertEquals("Course not found with ID : + id",e.getMessage());
        }
    }
}
