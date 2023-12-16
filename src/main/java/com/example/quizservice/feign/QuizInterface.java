package com.example.quizservice.feign;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.QuizAnswers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    //FROM QUESTION-SERVICE PROJECT!!!

    //generate quiz
    @GetMapping(path = "questions/generate", produces = "application/json")//<--- FUll URL IN PATH!!!!!
    ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numOfQuestions);

    //getQuestions (questionId)
    @PostMapping(path = "questions/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId);

    //calculate score
    @PostMapping(path = "questions/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<QuizAnswers> answersList);

}
