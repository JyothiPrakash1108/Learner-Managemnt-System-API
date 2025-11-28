package com.learnermanagement.LearnerManagementSystem.serviceTest;

import static org.mockito.Mockito.when;

import org.h2.command.dml.MergeUsing;
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

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    
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

}
