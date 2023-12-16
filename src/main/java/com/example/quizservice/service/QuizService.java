package com.example.quizservice.service;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.model.QuizDataTransferObject;

import java.util.List;

public interface QuizService {

    Quiz getQuiz(Integer quizId);
    String createQuiz(QuizDataTransferObject quizDTO);
    String deleteQuiz(Integer quizId);
    List<QuestionWrapper> getQuestionForQuiz(Integer id);
    Integer getScore(Integer id, List<QuizAnswers> answersList);

}
