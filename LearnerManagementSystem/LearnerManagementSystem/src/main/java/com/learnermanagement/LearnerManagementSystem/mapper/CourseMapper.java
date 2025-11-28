package com.learnermanagement.LearnerManagementSystem.mapper;

import com.learnermanagement.LearnerManagementSystem.dto.CohortDTO;
import com.learnermanagement.LearnerManagementSystem.dto.CourseDTO;
import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Cohort;
import com.learnermanagement.LearnerManagementSystem.entity.Course;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setCohorts(new ArrayList<>());
        List<CohortDTO> cohortDTOS = new ArrayList<>();
        for (Cohort cohort : course.getCohorts()){
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            cohortDTOS.add(cohortDTO);
        }
        courseDTO.setCohorts(cohortDTOS);
        return courseDTO;
    }
    public static List<CourseDTO> toCourseDTOS(List<Course> courses){
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses){
            CourseDTO courseDTO = toCourseDTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }
    public static CourseDTO toCourseDTOWithCohorts(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO = toCourseDTO(course);
        List<CohortDTO> cohortDTOS = new ArrayList<>();
        for (Cohort cohort : course.getCohorts()){
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            cohortDTO.setLearners(new ArrayList<>());
            cohortDTOS.add(cohortDTO);
        }
        courseDTO.setCohorts(cohortDTOS);
        return courseDTO;
    }
}
