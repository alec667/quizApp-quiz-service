package com.example.quizservice.controller;

import com.example.quizservice.model.Quiz;
import com.example.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping(path = "create")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }


}
