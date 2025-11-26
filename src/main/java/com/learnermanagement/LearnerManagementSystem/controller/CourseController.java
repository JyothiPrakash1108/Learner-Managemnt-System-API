package com.learnermanagement.LearnerManagementSystem.controller;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.CourseNotFoundException;
import com.learnermanagement.LearnerManagementSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.learnermanagement.LearnerManagementSystem.mapper.CohortMapper.toCohortDTOs;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    /*
    -> create course
    -> get all courses
    -> assign cohorts to course
    -> given course id and cohort id fetch all learners
     */
    @PostMapping("/courses")
    public CourseDTO createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }
    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }
    @GetMapping("/courses/{id}")
    public CourseDTO getcourseById(@PathVariable Long id) throws CourseNotFoundException {
        return courseService.getCourseById(id);
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity handleCourseNotFoundException(CourseNotFoundException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }
    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity handleCohortNotFoundException(CohortNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @PostMapping("/courses/{id}/cohorts")
    public CourseDTO assignCohortstoCourses(@PathVariable Long id, @RequestBody List<Long> cohortIds) throws CohortNotFoundException, CourseNotFoundException {
        return courseService.assignCohortsTOCourses(id,cohortIds);
    }
    @GetMapping("/courses/{id}/cohorts")
    public List<CohortDTO> getCohortsInCourse(@PathVariable Long id) throws CohortNotFoundException, CourseNotFoundException {
        List<Cohort> cohorts =  courseService.getCohortsInCourse(id);
        return toCohortDTOs(cohorts);
    }
}
