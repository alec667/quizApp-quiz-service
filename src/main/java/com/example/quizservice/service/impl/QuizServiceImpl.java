package com.example.quizservice.service.impl;

import com.example.quizservice.exception.QuizNotFoundException;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.QuizAnswers;
import com.example.quizservice.model.QuizDataTransferObject;
import com.example.quizservice.repository.QuizRepository;
import com.example.quizservice.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizInterface quizInterface;

    final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    @Override
    public Quiz getQuiz(Integer quizId) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            return optionalQuiz.get();
        } else {
            throw new QuizNotFoundException("Quiz not found :(");

        }
    }

    @Override
    public String createQuiz(QuizDataTransferObject quizDTO) {
        List<Integer> questionsIds = quizInterface.getQuestionsForQuiz(quizDTO.getCategory(), quizDTO.getNumOfQuestions()).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setQuestionsIds(questionsIds);
        quizRepository.save(quiz);

        return "Quiz created";

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
    public List<QuestionWrapper> getQuestionForQuiz(Integer id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        List<QuestionWrapper> questionsForUser;
        if (optionalQuiz.isPresent()) {
            questionsForUser = quizInterface.getQuestionsFromId(optionalQuiz.get().getQuestionsIds()).getBody();
            return questionsForUser;
        } else {
            throw new QuizNotFoundException("Quiz not found");
        }
    }

    @Override
    public Integer getScore(Integer id, List<QuizAnswers> answersList) {
        Integer score;
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            score = quizInterface.getScore(answersList).getBody();
            return score;
        } else {
            throw new QuizNotFoundException("Quiz not found:(");
        }
    }
}
