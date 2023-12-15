package com.example.quizservice.controller;

import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }

    @GetMapping(path = "{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Integer quizId) {
        return new ResponseEntity<>(quizService.getQuiz(quizId), HttpStatus.OK);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitAnswers(@PathVariable("quizId") Integer quizId, @RequestBody List<QuizAnswers> answersList) {
        return new ResponseEntity<>(quizService.getScore(quizId, answersList), HttpStatus.OK);
    }

    @DeleteMapping("{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("quizId") Integer quizId) {
        return new ResponseEntity<>(quizService.deleteQuiz(quizId), HttpStatus.OK);
    }


}
