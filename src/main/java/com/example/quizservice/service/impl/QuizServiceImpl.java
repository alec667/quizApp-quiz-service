package com.example.quizservice.service.impl;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.repository.QuizRepository;
import com.example.quizservice.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Quiz> optional = quizRepository.findById(quizId);
        if (optional.isPresent()) {
            quizRepository.deleteById(quizId);
            return "Quiz ID: " + quizId + " Deleted";
        } else {
            return "Quiz ID:" + quizId + " doesn't exist";
        }
    }

    @Override
    public List<QuestionWrapper> getQuestionForQuiz(int id) {
        return null;
    }

    @Override
    public Integer getScore(Integer id, List<QuizAnswers> answersList) {
        return null;
    }
}
