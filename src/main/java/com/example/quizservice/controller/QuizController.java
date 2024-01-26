package com.example.quizservice.controller;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.model.QuizDataTransferObject;
import com.example.quizservice.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping(path = "create")
    @Operation(summary = "Creates a quiz", description = "creates a quiz and saves it to database", responses = {@ApiResponse(responseCode = "201", description = "status CREATED")})
    public ResponseEntity<String> createQuiz(@RequestBody QuizDataTransferObject quizDTO) {
        return new ResponseEntity<>(quizService.createQuiz(quizDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "{quizId}")
    @Operation(summary = "Returns a quiz by ID", description = "Provides quiz details", responses = {@ApiResponse(responseCode = "200", description = "status OK")})
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Integer quizId) {
        return new ResponseEntity<>(quizService.getQuiz(quizId), HttpStatus.OK);
    }

    @GetMapping(path = "get/{id}")
    @Operation(summary = "Returns questions for quiz", description = "Provides a list of QuestionWrapper objects for the quiz", responses = {@ApiResponse(responseCode = "200", description = "status OK")})
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(quizService.getQuestionForQuiz(id), HttpStatus.OK);

    }

    @PostMapping("submit/{quizId}")
    @Operation(summary = "Submits answers and returns a score", description = "Sends a list of QuizAnswers objects to question-service API and obtains a score", responses = {
            @ApiResponse(responseCode = "200", description = "status OK")})
    public ResponseEntity<Integer> submitAnswers(@PathVariable("quizId") Integer quizId, @RequestBody List<QuizAnswers> answersList) {
        return new ResponseEntity<>(quizService.getScore(quizId, answersList), HttpStatus.OK);
    }

    @DeleteMapping("{quizId}")
    @Operation(summary = "Deletes a quiz by ID", description = "Deletes quiz from database", responses = {@ApiResponse(responseCode = "200", description = "status OK")})
    public ResponseEntity<String> deleteQuiz(@PathVariable("quizId") Integer quizId) {
        return new ResponseEntity<>(quizService.deleteQuiz(quizId), HttpStatus.OK);
    }


}
