package com.example.quizservice.model;

import lombok.Data;

@Data
public class QuizDataTransferObject {

    private String title;
    private String category;
    private Integer numOfQuestions;


}
