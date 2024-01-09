package com.example.quizservice.controller;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

    private AutoCloseable autoCloseable;
    final ObjectMapper objectMapper = new ObjectMapper();
    final ObjectWriter objectWriter = objectMapper.writer();
    QuizDataTransferObject quizDTO;
    Quiz testQuiz1;
    QuestionWrapper questionWrapper1, questionWrapper2;
    List<Integer> questionsIds;
    List<QuestionWrapper> questionsForQuiz;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
        questionWrapper1 = new QuestionWrapper(1, "What is the default value of float variable?", "0.0d", "0.0f", "0", "not defined");
        questionWrapper2 = new QuestionWrapper(2, "Which of the following is NOT a keyword in java?", "static", "Boolean", "void", "private");
        questionsIds = Arrays.asList(questionWrapper1.getId(), questionWrapper2.getId());
        testQuiz1 = new Quiz(1, "Quiz 1", questionsIds);
        questionsForQuiz = Arrays.asList(questionWrapper1, questionWrapper2);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createQuiz() throws Exception {
        quizDTO = new QuizDataTransferObject("Quiz 1", "Java", 1);
        String content = objectWriter.writeValueAsString(quizDTO);

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
    void getQuiz() throws Exception {
        when(quizService.getQuiz(1)).thenReturn(testQuiz1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/quiz/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Quiz 1")));
    }

    @Test
    void submitAnswers() throws Exception {
        QuizAnswers answer1 = new QuizAnswers(1, "0.0f");
        QuizAnswers answer2 = new QuizAnswers(2, "Boolean");
        List<QuizAnswers> answersList = Arrays.asList(answer1, answer2);
        String content = objectWriter.writeValueAsString(answersList);

        when(quizService.getScore(1, answersList)).thenReturn(2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/quiz/submit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getQuizQuestions() throws Exception {
        when(quizService.getQuestionForQuiz(1)).thenReturn(questionsForQuiz);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/quiz/get/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteQuiz() throws Exception {
        when(quizService.deleteQuiz(1)).thenReturn("Quiz ID: 1 Deleted");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/quiz/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertThat(result.getResponse().getContentAsString())
                                .isEqualTo("Quiz ID: 1 Deleted"));
    }
}