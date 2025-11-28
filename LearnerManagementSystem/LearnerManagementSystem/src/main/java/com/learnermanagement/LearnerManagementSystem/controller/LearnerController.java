package com.learnermanagement.LearnerManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnermanagement.LearnerManagementSystem.dto.LearnerDTO;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.exception.LearnerNotFoundException;
import static com.learnermanagement.LearnerManagementSystem.mapper.LearnerMapper.toLearnerDTOs;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

import jakarta.validation.Valid;

@RestController
public class LearnerController {
    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/learners")
    public Learner createLearner(@Valid @RequestBody Learner learner){
        return learnerManagementService.save(learner);
    }

    @GetMapping("/learners")
    public List<LearnerDTO> fetchAllLearners(){
        return toLearnerDTOs(learnerManagementService.getAllLearners());
    }

    @GetMapping("/learners/{id}")
    public Learner fetchLearnerById(@PathVariable("id") Long id) throws LearnerNotFoundException{
        return learnerManagementService.fetchLearnerById(id);
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    // handle validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
