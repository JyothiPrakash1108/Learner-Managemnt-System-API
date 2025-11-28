package com.learnermanagement.LearnerManagementSystem.controllerTest;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.learnermanagement.LearnerManagementSystem.controller.LearnerController;
import com.learnermanagement.LearnerManagementSystem.entity.Learner;
import com.learnermanagement.LearnerManagementSystem.service.LearnerManagementService;

@WebMvcTest(LearnerController.class)
@AutoConfigureMockMvc
public class LearnerControllerTest {
    @MockBean
    private LearnerManagementService learnerManagementService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void ShouldSaveAndReturnLearner() throws Exception {
        Learner learner = new Learner();
        learner.setName("John Doe");
        learner.setEmail("john@gmail.com");
        learner.setId(1L);
        when(learnerManagementService.save(any())).thenReturn(learner);

       mockMvc.perform(post("/learners").contentType(org.springframework.http.MediaType.APPLICATION_JSON)
        .content("{\"name\":\"John Doe\",\"email\":\"john@gmail.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("John Doe"));
    }

}
    
