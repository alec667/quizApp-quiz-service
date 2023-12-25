package com.example.quizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizAnswers {
    private Integer id;
    private String answer;
}
