package com.example.quizservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuizExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Object> quizNotFoundException(QuizNotFoundException quizNotFoundException){
        return new ResponseEntity<>(quizNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
