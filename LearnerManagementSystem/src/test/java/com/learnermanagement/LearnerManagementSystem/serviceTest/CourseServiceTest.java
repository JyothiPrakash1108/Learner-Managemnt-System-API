package com.learnermanagement.LearnerManagementSystem.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.h2.command.dml.MergeUsing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.CourseNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.CourseRepository;
import com.learnermanagement.LearnerManagementSystem.service.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CohortRepository cohortRepository;
    //this test has no meaning as it only delegates to repository save method
    //but included to show example of unit test with mockito
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
        verify(courseRepository, times(1)).save(course);
    }
    @Test
    public void givenCourseId_whenGetCourseById_thenReturnCourse() throws Exception {
        //Arrange
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Frontend Development with React");
        course.setCourseDescription("Learn frontend development using React.js and Redux");
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        //Act
        CourseDTO fetchedCourse = courseService.getCourseById(courseId);

        //Assert
        assertEquals(course.getCourseName(), fetchedCourse.getCourseName());
        assertEquals(course.getCourseDescription(), fetchedCourse.getCourseDescription());
        verify(courseRepository, times(1)).findById(courseId);
    }
    @Test
    public void givenNonExistingCourseId_whenGetCourseById_thenThrowNotFoundException() {
        
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Frontend Development with React");
        course.setCourseDescription("Learn frontend development using React.js and Redux");
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

    
        try {
            courseService.getCourseById(courseId);
        } catch (Exception e) {
            assertEquals("course not found with ID : "+courseId, e.getMessage());
        }
        verify(courseRepository, times(1)).findById(courseId);
    }
    @Test
    public void givenCohortsAndCourses_whenAssigning_thenCohortsAreAssignedToCourses() throws CourseNotFoundException, CohortNotFoundException{
        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Data Science with Python");
        course.setCourseDescription("Learn data science using Python and its libraries");

        Cohort cohort1 = new Cohort();
        cohort1.setId(1L);
        cohort1.setCohortName("DS101");
        cohort1.setCohortDescription("Starts from jan 2026");
        Cohort cohort2 = new Cohort();
        cohort2.setId(2L);
        cohort2.setCohortName("DS102");
        cohort2.setCohortDescription("Starts from feb 2026");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(cohortRepository.findById(1L)).thenReturn(Optional.of(cohort1));
        when(cohortRepository.findById(2L)).thenReturn(Optional.of(cohort2));

       
        CourseDTO updatedCourse = courseService.assignCohortsTOCourses(1L,List.of(1L,2L));
       
        assertEquals(2, updatedCourse.getCohorts().size());
        verify(courseRepository, times(1)).findById(1L);
        verify(cohortRepository, times(1)).findById(1L);
        verify(cohortRepository, times(1)).findById(2L);
        assertEquals(cohort1.getCohortName(), updatedCourse.getCohorts().get(0).getCohortName());
    }

}
