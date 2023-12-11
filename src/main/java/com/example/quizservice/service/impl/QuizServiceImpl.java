package com.example.quizservice.service.impl;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.repository.QuizRepository;
import com.example.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz getQuiz(Integer quizId) {
        return null;
    }

    @Override
    public String createQuiz(Quiz quiz) {
        return null;
    }

    @Override
    public String deleteQuiz(Integer quizId) {
        return null;
    }

    @Override
    public List<QuestionWrapper> getQuestionForQuiz(int id) {
        return null;
    }

    @Override
    public Integer calculateResult(Integer id, List<QuizAnswers> answersList) {
        return null;
    }
}
