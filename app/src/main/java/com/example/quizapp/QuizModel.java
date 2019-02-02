package com.example.quizapp;

import java.util.ArrayList;

public class QuizModel {
    private String question;
    private String answer;
    private ArrayList<QuizModel> questions;
    public QuizModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public QuizModel() {

    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public   ArrayList<QuizModel> questions_answer(){
        questions = new ArrayList<>();
        questions.add(new QuizModel("1+3","4"));
        questions.add(new QuizModel("7+3","10"));
        questions.add(new QuizModel("20/5","4"));
        questions.add(new QuizModel("7/2","3.5"));
        questions.add(new QuizModel("7*3","21"));

        return questions;
    }


}
