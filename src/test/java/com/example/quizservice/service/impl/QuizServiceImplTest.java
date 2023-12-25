package com.example.quizservice.service.impl;

import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizDataTransferObject;
import com.example.quizservice.repository.QuizRepository;
import com.example.quizservice.service.QuizService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuizServiceImplTest {

    @Mock
    QuizRepository quizRepository;
    @Mock
    QuizInterface quizInterface;

    private QuizService quizService;
    private AutoCloseable autoCloseable;
    QuizDataTransferObject quizDTO;
    Quiz testQuiz1;
    QuestionWrapper questionWrapper1, questionWrapper2;
    List<Integer> questionsIds;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.quizService = new QuizServiceImpl(quizRepository, quizInterface);
        questionWrapper1 = new QuestionWrapper(1, "What is the default value of float variable?", "0.0d", "0.0f", "0", "not defined");
        questionWrapper2 = new QuestionWrapper(2, "Which of the following is NOT a keyword in java?", "static", "Boolean", "void", "private");
        quizDTO = new QuizDataTransferObject("Quiz 1", "Java", 2);
        questionsIds = Arrays.asList(questionWrapper1.getId(), questionWrapper2.getId());
        testQuiz1 = new Quiz(1, "Quiz 1", questionsIds);
        quizRepository.save(testQuiz1);
    }

    @AfterEach
    void tearDown() throws Exception {
        quizRepository.deleteAll();
        autoCloseable.close();
    }

    @Test
    void getQuiz() {
        mock(Quiz.class);
        mock(QuizRepository.class);

        when(quizRepository.findById(1)).thenReturn(Optional.ofNullable(testQuiz1));
        assertThat(quizService.getQuiz(1)).isEqualTo(testQuiz1);
    }

    @Test
    void createQuiz() {
        mock(Quiz.class);
        mock(QuizRepository.class);
        mock(QuizInterface.class);

        ResponseEntity<List<Integer>> response = new ResponseEntity<>(questionsIds, HttpStatus.OK);
        when(quizInterface.getQuestionsForQuiz(quizDTO.getCategory(), quizDTO.getNumOfQuestions())).thenReturn(response);
        when(quizRepository.save(testQuiz1)).thenReturn(testQuiz1);
        assertThat(quizService.createQuiz(quizDTO)).isEqualTo("Quiz created");
    }

    @Test
    void deleteQuiz() {
    }

    @Test
    void getQuestionForQuiz() {
    }

    @Test
    void getScore() {
    }
}