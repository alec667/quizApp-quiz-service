package com.example.quizservice.controller;

import com.example.quizservice.model.QuizDataTransferObject;
import com.example.quizservice.service.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    AutoCloseable autoCloseable;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    QuizDataTransferObject quizDTO;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createQuiz() throws Exception {
        quizDTO = new QuizDataTransferObject("Quiz 1", "Java", 1);
        String content =objectWriter.writeValueAsString(quizDTO);
        when(quizService.createQuiz(quizDTO)).thenReturn("Quiz created");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/quiz/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(result ->
                        assertThat(result.getResponse().getContentAsString())
                                .isEqualTo("Quiz created"));
    }

    @Test
    void getQuiz() {
    }

    @Test
    void submitAnswers() {
    }

    @Test
    void deleteQuiz() {
    }
}