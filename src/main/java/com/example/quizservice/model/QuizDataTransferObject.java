package com.example.quizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDataTransferObject {

    private String title;
    private String category;
    private Integer numOfQuestions;


}
