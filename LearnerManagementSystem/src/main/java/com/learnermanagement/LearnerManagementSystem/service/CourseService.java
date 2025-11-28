<<<<<<< HEAD
package com.learnermanagement.LearnerManagementSystem.service;

import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.CourseNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.CourseRepository;

import jakarta.transaction.Transactional;

import org.hibernate.annotations.TimeZoneStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import static com.learnermanagement.LearnerManagementSystem.mapper.CourseMapper.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CohortRepository cohortRepository;
    public CourseDTO createCourse(Course course) {
        return toCourseDTO(courseRepository.save(course));
    }

    public List<CourseDTO> getAllCourses() {
        List<Course>  courses =  courseRepository.findAll();
        return toCourseDTOS(courses);
    }

    public CourseDTO getCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("course not found with ID : "+id);
        }
        return toCourseDTO(optionalCourse.get());
    }
    @Transactional
    public CourseDTO assignCohortsTOCourses(Long id, List<Long> cohortIds) throws CourseNotFoundException, CohortNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("Course not found with ID : "+ id);
        }
        Course course = optionalCourse.get();
        List<Cohort> cohorts = new ArrayList<>();
        for (Long cohortId : cohortIds) {
            Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
            if (!optionalCohort.isPresent()) {
                throw new CohortNotFoundException("cohort not found with exception ID : " + cohortId);
            }
            optionalCohort.get().setCourse(course);
            cohorts.add(optionalCohort.get());
        }
        course.setCohorts(cohorts);
        return toCourseDTOWithCohorts(courseRepository.save(course));
    }

    public List<Cohort> getCohortsInCourse(Long id) throws CourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("Course not found with ID : "+ id);
        }
        Course course = optionalCourse.get();
        List<Cohort> cohorts = course.getCohorts();
        return cohorts;
    }

    
}
=======
package com.learnermanagement.LearnerManagementSystem.service;

import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.exception.CohortNotFoundException;
import com.learnermanagement.LearnerManagementSystem.exception.CourseNotFoundException;
import com.learnermanagement.LearnerManagementSystem.repository.CohortRepository;
import com.learnermanagement.LearnerManagementSystem.repository.CourseRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import static com.learnermanagement.LearnerManagementSystem.mapper.CourseMapper.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CohortRepository cohortRepository;
    public CourseDTO createCourse(Course course) {
        return toCourseDTO(courseRepository.save(course));
    }

    public List<CourseDTO> getAllCourses() {
        List<Course>  courses =  courseRepository.findAll();
        return toCourseDTOS(courses);
    }

    public CourseDTO getCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("course not found with ID : "+id);
        }
        return toCourseDTO(optionalCourse.get());
    }
    @Transactional
    public CourseDTO assignCohortsTOCourses(Long id, List<Long> cohortIds) throws CourseNotFoundException, CohortNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("Course not found with ID : "+ id);
        }
        Course course = optionalCourse.get();
        List<Cohort> cohorts = new ArrayList<>();
        for (Long cohortId : cohortIds) {
            Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
            if (!optionalCohort.isPresent()) {
                throw new CohortNotFoundException("cohort not found with exception ID : " + cohortId);
            }
            optionalCohort.get().setCourse(course);
            cohorts.add(optionalCohort.get());
        }
        course.setCohorts(cohorts);
        return toCourseDTOWithCohorts(courseRepository.save(course));
    }

    public List<Cohort> getCohortsInCourse(Long id) throws CourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            throw new CourseNotFoundException("Course not found with ID : "+ id);
        }
        Course course = optionalCourse.get();
        List<Cohort> cohorts = course.getCohorts();
        return cohorts;
    }
}
>>>>>>> fe99840de0a1cc86501a349676a2aff64b35115a
